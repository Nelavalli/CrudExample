package com.dataBytes.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="EMPLOYEE_DC")
@Audited
public class EmployeeDevCenter {
	
	private static final long serialVersionUID = 453693552059515151L;
	
	@Id
	@Column(name="EMPLOYEE_DC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EMPLOYEE_DC_SEQ")
	@SequenceGenerator(name = "EMPLOYEE_DC_SEQ", sequenceName = "EMPLOYEE_DC_SEQ", allocationSize = 1, initialValue = 1)	
	@Getter @Setter private Long id;
	
	@Getter @Setter @JsonProperty @Column (name="EMPLOYEE_ID")
	private Boolean employeeId;
	
	@Getter @Setter @JsonProperty @Column (name="DC_ID")
	private Boolean dcID;
	
	@Getter @Setter @JsonProperty
	@OneToMany(cascade = CascadeType.ALL)
	 @JoinTable(
			 name = "EMPLOYEE_DC_PRIVILEGE",
			 joinColumns = @JoinColumn(table="EMPLOYEE_DC",name = "EMPLOYEE_DC_ID", referencedColumnName="EMPLOYEE_DC_ID"),
	         inverseJoinColumns = {
	        		 @JoinColumn(table="PRIVILEGE", name = "PRIVILEGE_ID", referencedColumnName="PRIVILEGE_ID"),
	         }
	 )	
	private List<Privilege> privileges;
	
	@Getter @Setter @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="LASTMODIFIED")
	private Date lastModified;
	
}

package com.dataBytes.dto;

import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="DEVELOPMENTCENTER")
@Audited
public class DevelopmentCenter {
	
	private static final long serialVersionUID = 453693552059515161L;
	@Id
	@Getter @Setter @JsonProperty @Column(name="DC_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DC_SEQ")
	@SequenceGenerator(name = "DC_SEQ", sequenceName = "DC_SEQ", allocationSize = 1, initialValue = 1)	
	private Long id;

	@Getter @Setter @JsonProperty @Column (name="NAME")
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL)
	 @JoinTable(
			 name = "DC_PRIVILEGE",
			 joinColumns = @JoinColumn(table="DEVELOPMENTCENTER",name = "DC_ID", referencedColumnName="DC_ID"),
	         inverseJoinColumns = {
	        		 @JoinColumn(table="PRIVILEGE", name = "PRIVILEGE_ID", referencedColumnName="PRIVILEGE_ID"),
	         }
	 )
	@Getter @Setter @JsonProperty 
	private Set<Privilege> dcPrivileges;
	
	@Getter @Setter @Column (name="DELETEFLAG")
	private Boolean deleteFlag;
	
	@Getter @Setter @Column (name="LASTMODIFIEDBY")
	private String lastModifiedBy;
	
	@Temporal(TemporalType.DATE)
	@Getter @Setter @Column (name="LASTMODIFIED")
	private Date lastModified;
}

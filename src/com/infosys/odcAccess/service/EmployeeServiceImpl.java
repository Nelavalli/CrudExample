package com.infosys.odcAccess.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infosys.odcAccess.dao.EmployeeDAO;
import com.infosys.odcAccess.dto.Employee;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDAO employeeDAO;

	Employee findById(long id) {
	}
    
	List<Employee> findByName(String name){
	}
	
	List<Employee> findByIdOrName(String idOrname);
     
    boolean saveEmployee(Employee employee);
     
    boolean updateUser(Employee employee);
     
    boolean deleteEmployeeById(long id);
 
    List<Employee> findAllEmployees(); 
     
    boolean deleteAllEmployees();
     
    public boolean isEmployeeExist(Employee employee);
}
}

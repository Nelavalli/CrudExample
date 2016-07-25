package com.infosys.odcAccess.service;

import java.util.List;

import com.infosys.odcAccess.dto.Employee;

public interface EmployeeService {
	
	Employee findById(long id);
    
	List<Employee> findByName(String name);
	
	List<Employee> findByIdOrName(String idOrname);
     
    boolean saveEmployee(Employee employee);
     
    boolean updateUser(Employee employee);
     
    boolean deleteEmployeeById(long id);
 
    List<Employee> findAllEmployees(); 
     
    boolean deleteAllEmployees();
     
    public boolean isEmployeeExist(Employee employee);
}

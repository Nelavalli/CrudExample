package com.dataBytes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dataBytes.dao.EmployeeDAO;
import com.dataBytes.dto.Employee;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	public Employee findById(long id) {
		return employeeDAO.getById(id);
	}
    
	@Override
	public boolean isExist(long id) {
		return employeeDAO.isExist(id);
	}
	
	@Override
	public List<Employee> findByName(String name){
		return employeeDAO.getByName(name, -1, -1);
	}
	
	@Override
	public List<Employee> findByName(String name, int firstResult, int maxResults){
		return employeeDAO.getByName(name, firstResult, maxResults);
	}
	
	@Override
	public List<Employee> findByIdOrName(String idOrname) {
		return employeeDAO.get(idOrname, -1, -1);
	}
	
	@Override
	public List<Employee> findByIdOrName(String idOrname, int firstResult, int maxResults) {
		return employeeDAO.get(idOrname, firstResult, maxResults);
	}
	
	@Override
    public boolean add(Employee employee) {
		try {
			employeeDAO.add(employee);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
     
    public boolean update(Employee employee) {
    	try {
			employeeDAO.update(employee);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    }
     
    public boolean delete(long id) {
    	try {
			employeeDAO.delete(id);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
    }
 
    public List<Employee> findAllEmployees() {
    	return employeeDAO.list(-1,-1);
    }
      
    
}

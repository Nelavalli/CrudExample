package com.infosys.odcAccess.dao;

import java.util.List;
import java.util.Set;

import com.infosys.odcAccess.dto.Employee;

public interface EmployeeDAO {

	public List<Employee> list(int firstResult, int maxResults);
	public void add(Employee item);
	public void  update(Employee item);
	public Employee  getById(Long id);
	public List<Employee> getByName(String name, int firstResult, int maxResults);
	public List<Employee>  get(String idOrName, int firstResult, int maxResults);
	public void  delete(Long id);
	
}

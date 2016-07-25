package com.infosys.odcAccess.dao;

import java.util.List;
import java.util.Set;

import com.infosys.odcAccess.dto.Employee;

public interface EmployeeDAO {

	public List<Employee> list(int firstPage, int lastPage);
	public void add(Employee item);
	public void  update(Employee item);
	public Employee  get(Long id);
	public List<Employee>  get(String idOrName, int firstPage, int lastPage);
	public void  delete(Long id);
	
}

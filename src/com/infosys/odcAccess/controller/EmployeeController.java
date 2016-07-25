package com.infosys.odcAccess.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.infosys.odcAccess.dao.EmployeeDAO;
import com.infosys.odcAccess.dto.Employee;
import com.infosys.odcAccess.mail.MailHandler;
import com.infosys.odcAccess.resouce.Employee_bak;
import com.infosys.odcAccess.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MailHandler mailHandler;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public List<Employee> getEmployeesJson() {
		log.debug("Entered into EmployeesJson");
		List<Employee> employees = employeeService.findAllEmployees();
		return employees;
	}
	
	@RequestMapping(value = "/employee/{idOrName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployeesJson(@RequestParam(value = "idOrName", required = true) String idOrName) {
		List<Employee> employeeList = employeeService.findByIdOrName(idOrName);
		ArrayList<Employee> employees = new ArrayList<Employee>();
		return employees;
	}
	/*
	@RequestMapping(value = "/listEmployee", method = RequestMethod.GET)
	public ModelAndView list() {
		System.out.println("We are in list block");
		List<Employee> employeeList = employeeDAO.list(-1, -1);
		ModelAndView model = new ModelAndView("ListEmployee");
		model.addObject("employeeList", employeeList);
		return model;
	}
	
	@RequestMapping(value = "/addEmployee", method = RequestMethod.GET)
	public ModelAndView add() {
		log.debug("Entered into add");	
		ModelAndView model = new ModelAndView("AddEmployee");
		model.addObject("employee", new Employee());
		List<Employee> employeeList = employeeDAO.list(-1, -1);
		model.addObject("employeeList", employeeList);
		return model;
	}
	
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(value = "id", required = true) Long id) {
		System.out.println("Id= " + id);
		ModelAndView model = new ModelAndView("AddEmployee");
		Employee employee = employeeDAO.get(id);
		model.addObject("employee", employee);
		List<Employee> employeeList = employeeDAO.list(-1,-1);
		model.addObject("employeeList", employeeList);
		return model;
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id", required = true) Long id) {
		ModelAndView model = new ModelAndView("AddEmployee");
		employeeDAO.delete(id);
		model.addObject("employee", new Employee());
		List<Employee> employeeList = employeeDAO.list(-1,-1);
		model.addObject("employeeList", employeeList);
		mailHandler.sendMail("Employee Remove", "Employee Removed Successfully");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("employee") Employee employee) {
		log.info("Input employee object: "+employee);
		if (null != employee)			
			employeeDAO.add(employee);

		ModelAndView model = new ModelAndView("AddEmployee");
		model.addObject("employee", employee);
		List<Employee> employeeList = employeeDAO.list(-1, -1);
		model.addObject("employeeList", employeeList);
		mailHandler.sendMail("Employee Add", "Employee Added Successfully");
		return model;
	}
	
	*/
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> add(@ModelAttribute("employee") Employee employee, UriComponentsBuilder ucBuilder) {
		log.info("Input employee object: "+employee);
		if (null != employee) {			
			employeeService.add(employee);
		} else {
			//if (userService.isUserExist(user)) {
	           // System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        //}
		}
		
		mailHandler.sendMail("Employee Add", "Employee Added Successfully");
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@ModelAttribute("employee") Employee employee, UriComponentsBuilder ucBuilder) {
		log.info("Input employee object: "+employee);
		if (null != employee) {			
			employeeService.update(employee);
		} else {
			//if (userService.isUserExist(user)) {
	           // System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        //}
		}
		
		mailHandler.sendMail("Employee Add", "Employee Added Successfully");
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable long id) {
		log.info("Input id value: "+id);
		if (id !=  0) {			
			employeeService.delete(id);
		} else {
			//if (userService.isUserExist(user)) {
	           // System.out.println("A User with name " + user.getName() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        //}
		}
		
		mailHandler.sendMail("Employee Add", "Employee Added Successfully");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	/*
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.PUT)
	public ModelAndView update(@ModelAttribute("employeeMap") Employee employee) {
		System.out.println(employee);
		if (null != employee)
			employeeDAO.update(employee);

		ModelAndView model = new ModelAndView("AddEmployee");
		model.addObject("employee", employee);
		List<Employee> employeeList = employeeDAO.list(-1,-1);
		model.addObject("employeeList", employeeList);
		mailHandler.sendMail("Employee Update", "Employee Updated Successfully");
		return model;
	}
	*/
}

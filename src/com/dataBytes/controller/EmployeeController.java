package com.dataBytes.controller;

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

import com.dataBytes.dao.EmployeeDAO;
import com.dataBytes.dto.Employee;
import com.dataBytes.mail.MailHandler;
import com.dataBytes.resouce.Employee_bak;
import com.dataBytes.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MailHandler mailHandler;
	
	private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

	
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
	
	@RequestMapping(value = "/admin/home")
	public ModelAndView displayHomePage() {
		log.debug("displayHomePage");
		ModelAndView model = new ModelAndView("home");
		List<Employee> employees = employeeService.findAllEmployees();
		model.addObject("employees", employees);
		return model;
	}
	
	@RequestMapping(value = "/admin/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesJson() {
		
		List<Employee> employees;		
		try {
			employees = employeeService.findAllEmployees();
			if (employees == null || employees.isEmpty()) {
				return new ResponseEntity<List<Employee>>(employees,HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
			
		} catch(Exception e) {
			log.error("Exception occured in employeeService calling", e);
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(value = "/admin/employee/{idOrName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getEmployeesJson(@PathVariable(value = "idOrName") String idOrName) {
		List<Employee> employees;
		
		try {
			employees = employeeService.findByIdOrName(idOrName);
			if (employees == null || employees.isEmpty()) {
				return new ResponseEntity<List<Employee>>(employees,HttpStatus.NO_CONTENT);
			}			
			
		} catch(Exception e) {
			log.error("Exception occured in employeeService calling for idOrName ="+idOrName, e);
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<Employee>>(employees,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = {"/guest/employee/{id}","/admin/employee/{id}"}, method = RequestMethod.POST)
	public ResponseEntity<?> add(@ModelAttribute("employee") Employee employee, UriComponentsBuilder ucBuilder) {
		
		if (null == employee) {			
			
			String msg = "Can not support request with empty employee object";
			log.info(msg);			
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
			
		} else {

			if (employeeService.isExist(employee.getId())) {
				String msg = "A Employee with Id " + employee.getId() + " already exist";
				log.info(msg);
				return new ResponseEntity<String>(msg,HttpStatus.CONFLICT);				
			}
		}
		
		try {
			employeeService.add(employee);
			
		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe ="+employee, e);
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		mailHandler.sendMail("Employee Add", "Employee Added Successfully");
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/admin/employee/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@ModelAttribute("employee") Employee employee, UriComponentsBuilder ucBuilder) {		
		
		if (null == employee) {						

			String msg = "Can not support request with empty employee object";
			log.info(msg);			
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
			
		} else {

			if (!employeeService.isExist(employee.getId())) {
				String msg = "A Employee with Id " + employee.getId() + " does not exist";
				log.info(msg);
				return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);
				
			} 
			
		}
		
		try {
			employeeService.update(employee);
			
		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe ="+employee, e);
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}		
		
		mailHandler.sendMail("Employee Update", "Employee updated Successfully");
		HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(employee.getId()).toUri());
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
	@RequestMapping(value = "/admin/employee/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable long id) {
		
		if (id ==  0) {	
			String msg = "Can not support request with employee id is 0 or empty";
			log.info(msg);	
			return new ResponseEntity<String>( msg,HttpStatus.CONFLICT);
		
		} else if (!employeeService.isExist(id)) {
				String msg = "A Employee with Id " + id+ " does not exist";
				log.info(msg);
				return new ResponseEntity<String>(msg,HttpStatus.NOT_FOUND);				
		} 		
		
		try {
			employeeService.delete(id);
			
		} catch (Exception e) {
			log.error("Exception occured in employeeService calling for employe ID ="+id, e);
			String errorMessage = e + " <== error";
	        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		mailHandler.sendMail("Employee Delete", "Employee deleted Successfully");
		return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
	}
	
}

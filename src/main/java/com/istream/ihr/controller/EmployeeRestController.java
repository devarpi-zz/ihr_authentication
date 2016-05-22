package com.istream.ihr.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.istream.ihr.service.EmployeeService;
import com.istream.ihr.vo.orm.Employee;

@RestController
public class EmployeeRestController {
	
	@Autowired
    EmployeeService employeeService;  //Service which will do all data retrieval/manipulation work
 
    
    //-------------------Retrieve All Users--------------------------------------------------------
     
    @RequestMapping(value = "/employee/", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<com.istream.ihr.vo.EmployeeVO>> listAllEmployees() {
        List<Employee> employees = employeeService.findAllEmployees();
        List<com.istream.ihr.vo.EmployeeVO> employeeVO = translate(employees);
        if(employees.isEmpty()){
            return new ResponseEntity<List<com.istream.ihr.vo.EmployeeVO>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<com.istream.ihr.vo.EmployeeVO>>(employeeVO, HttpStatus.OK);
    }
    
    private List<com.istream.ihr.vo.EmployeeVO> translate(List<Employee> employees){
    	List<com.istream.ihr.vo.EmployeeVO> vos = new ArrayList<com.istream.ihr.vo.EmployeeVO>();
    	for(Employee emp: employees) {
    		com.istream.ihr.vo.EmployeeVO vo= new com.istream.ihr.vo.EmployeeVO();
    		vo.setEmail(emp.getEmail());
    		vo.setId(emp.getEmployeeId());
    		vo.setFirstName(emp.getFirstName());
    		vo.setHomePhone(emp.getHomePhone());
    		vo.setLastName(emp.getLastName());
    		vo.setMiddleName(emp.getMiddleName());
    		vo.setMobilePhone(emp.getMobilePhone());
    		vo.setWorkPhone(emp.getWorkPhone());
    		vos.add(vo);
    		
    	}
    	return vos;
    }
 
    //-------------------Search Users--------------------------------------------------------
    @RequestMapping(value = "/employee/{name}/{client}", method = RequestMethod.GET, headers="Accept=application/json")
    public ResponseEntity<List<Employee>> searchEmployees(@PathVariable("name") String name, @PathVariable("client") String client) {
        List<Employee> employees = employeeService.searchEmployees(name, client);
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
   
    
    //-------------------Retrieve Single User--------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> getEmployee(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
 
     
     
    //-------------------Create a User--------------------------------------------------------
     
    @RequestMapping(value = "/employee/", method = RequestMethod.POST)
    public ResponseEntity<Void> createEmployee(@RequestBody Employee employee,    UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + employee.getFirstName());
 
        employeeService.saveEmployee(employee);
 
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/employee/{id}").buildAndExpand(employee.getEmployeeId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
 
    
     
    //------------------- Update a User --------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        System.out.println("Updating User " + id);
         
        Employee currentEmployee = employeeService.findById(id);
         
        if (currentEmployee==null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        currentEmployee.setFirstName(employee.getFirstName());
         
        employeeService.updateEmployee(currentEmployee);
        return new ResponseEntity<Employee>(currentEmployee, HttpStatus.OK);
    }
 
    
    
    //------------------- Delete a User --------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting User with id " + id);
 
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
 
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<Employee>(HttpStatus.NO_CONTENT);
    }
 
     
 
    
}

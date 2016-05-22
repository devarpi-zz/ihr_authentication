package com.istream.ihr.service;

import java.util.List;

import com.istream.ihr.vo.orm.Employee;

public interface EmployeeService {
	
	Employee findById(long id);
	
	Employee findByName(String name);
	
	void saveEmployee(Employee employee);
	
	void updateEmployee(Employee employee);
	
	void deleteEmployeeById(long id);

	List<Employee> findAllEmployees(); 
	
	List<Employee> searchEmployees(String name, String client);
	
}

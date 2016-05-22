package com.istream.ihr.service.impl;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.istream.ihr.service.EmployeeService;
import com.istream.ihr.vo.dao.EmployeeDao;
import com.istream.ihr.vo.orm.Employee;

@Service("employeeService")

public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	private static final AtomicLong counter = new AtomicLong();
	
	
	public List<Employee> findAllEmployees() {
		return this.employeeDao.selectAll();
	}
	
	public Employee findById(long id) {
		return employeeDao.findById(id);
	}
	
	public Employee findByName(String name) {
		return null;
	}
	
	public void saveEmployee(Employee employee) {
	}

	public void updateEmployee(Employee employee) {
	}

	public void deleteEmployeeById(long id) {
	}

	public boolean isEmployeeExist(Employee employee) {
		return true;
	}
	
	@Override
	public List<Employee> searchEmployees(String name, String client) {
		return null;
	}
}

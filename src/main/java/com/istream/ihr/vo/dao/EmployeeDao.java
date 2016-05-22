package com.istream.ihr.vo.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.istream.ihr.vo.orm.Employee;
 
@Repository
public class EmployeeDao {
 

private SessionFactory sessionFactory;
 
public SessionFactory getSessionFactory() {
return sessionFactory;
}
 
public Employee findById(long id) {
	int empID = (int)id;
	Session session = getSessionFactory().openSession();
	Employee employee = session.get(Employee.class, empID);
	return employee;
}

@Autowired
public void setSessionFactory(SessionFactory sessionFactory) {
this.sessionFactory = sessionFactory;
}
 
public void saveEmployee(Employee person) {
Session session = getSessionFactory().openSession();
session.saveOrUpdate(person);

}
 
public List<Employee> selectAll() {
Session session = getSessionFactory().openSession();
Criteria criteria = session.createCriteria(Employee.class);
List<Employee> persons = (List<Employee>) criteria.list();
return persons;
}
 
public List<Employee> searchEmployee() {
Session session = getSessionFactory().openSession();
Criteria criteria = session.createCriteria(Employee.class);
List<Employee> persons = (List<Employee>) criteria.list();
return persons;
}
}

package com.zit.hibernate.entities;

import static org.junit.Assert.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitTest {
	
	SessionFactory sessionFactory = null;
	Session session = null;
	Transaction transaction = null;

	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder()
								.applySettings(configuration.getProperties())
								.buildServiceRegistry();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	
	
	
	@Test
	public void testCollectionSecondLevelCache() {
		Department dept = (Department) session.get(Department.class, 2);
		System.out.println(dept.getName());
		System.out.println(dept.getEmps().size());
		
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		
		Department dept2 = (Department) session.get(Department.class, 2);
		System.out.println(dept2.getName());
		System.out.println(dept2.getEmps().size());
	}
	
	
	@Test
	public  void test() {
		
	}
	
	
	@Test
	public void test1() {
		Employee employee = (Employee) session.get(Employee.class, 2);
		System.out.println(employee);
		
		transaction.commit();
		session.close();
		
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
		
		Employee employee2 = (Employee) session.get(Employee.class, 2);
		System.out.println(employee2);
		
	}

}

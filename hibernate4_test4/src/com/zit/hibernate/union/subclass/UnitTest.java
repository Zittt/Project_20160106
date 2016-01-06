package com.zit.hibernate.union.subclass;


import java.util.List;

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
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Before
	public void init() {
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = 
				new ServiceRegistryBuilder().applySettings(configuration.getProperties())
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
	public void test1() {
		
	}
	
	
	@Test
	public void testQuery() {
		List<Person> list = session.createQuery("FROM Person").list();
		System.out.println(list.size());
		
		List<Student> list2 = session.createQuery("FROM Student").list();
		System.out.println(list2.size());
		
	}
	
	
	/**
	 * 辨别者列由 Hibernate自动维护
	 */
	
	@Test
	public void testSave() {
		
		
		Person person = new Person();
		person.setAge(40);
		person.setName("CC");
		
		session.save(person);
		
		Student stu = new Student();
		stu.setAge(60);
		stu.setSchool("CC-school");
		stu.setName("CC-s");
		session.save(stu);
	}
	

	@Test
	public void testUpdate() {
		
	}
	
}

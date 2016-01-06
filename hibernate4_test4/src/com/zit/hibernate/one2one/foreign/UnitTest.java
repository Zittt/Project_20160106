package com.zit.hibernate.one2one.foreign;


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
	public void testSave() {
		Department department = new Department();
		department.setDeptName("DEPT-AA");
		
		Manager manager = new Manager();
		manager.setMgrName("MGR-AA");
		
		department.setMgr(manager);
		manager.setDept(department);
		
		session.save(manager);
		session.save(department);
	}
	

	@Test
	public void test2() {
		Department department = (Department) session.get(Department.class, 1);
		System.out.println(department.getDeptName());
		Manager mgr = department.getMgr();
		
		System.out.println(mgr.getMgrName());
	}
	
}

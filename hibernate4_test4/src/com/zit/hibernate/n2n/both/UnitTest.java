package com.zit.hibernate.n2n.both;


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
	public  void test() {
		
	}
	
	
	@Test
	public void test1() {
		Category cat1 = new Category();
		cat1.setName("cat1");
		Category cat2 = new Category();
		cat2.setName("cat2");
		Category cat3 = new Category();
		cat3.setName("cat3");
		
		
		Item item1 = new Item();
		item1.setName("item1");
		Item item2 = new Item();
		item2.setName("item2");
		Item item3 = new Item();
		item3.setName("item3");
		Item item4 = new Item();
		item4.setName("item4");
		
		
		cat1.getItems().add(item1);
		cat2.getItems().add(item1);
		cat3.getItems().add(item1);
		
		cat1.getItems().add(item2);
		cat2.getItems().add(item2);
		cat3.getItems().add(item2);
		
		cat1.getItems().add(item3);
		cat2.getItems().add(item3);
		cat3.getItems().add(item3);

		cat1.getItems().add(item4);
		cat2.getItems().add(item4);
		cat3.getItems().add(item4);
		
		
		item1.getCategories().add(cat1);
		item1.getCategories().add(cat2);
		item1.getCategories().add(cat3);
		
		item2.getCategories().add(cat1);
		item2.getCategories().add(cat2);
		item2.getCategories().add(cat3);
		
		item3.getCategories().add(cat1);
		item3.getCategories().add(cat2);
		item3.getCategories().add(cat3);
		
		item4.getCategories().add(cat1);
		item4.getCategories().add(cat2);
		item4.getCategories().add(cat3);
		
		
		session.save(item1);
		session.save(item2);
		session.save(item3);
		session.save(item4);
		
		session.save(cat1);
		session.save(cat2);
		session.save(cat3);
		
	}

	@Test
	public void test2() {
		Category cat = (Category) session.get(Category.class, 1);
		System.out.println(cat.getName());
		System.out.println("***********************");
		System.out.println(cat.getItems().size());
		System.out.println("***********************");
		System.out.println(cat.getItems().iterator().next().getName());
	}
	
}

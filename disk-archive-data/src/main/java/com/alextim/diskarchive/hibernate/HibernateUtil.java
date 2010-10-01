package com.alextim.diskarchive.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(); 
		}
	}
	
	public Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}
}

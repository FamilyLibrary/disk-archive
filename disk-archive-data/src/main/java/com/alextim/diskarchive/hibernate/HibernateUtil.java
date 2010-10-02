package com.alextim.diskarchive.hibernate;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil extends GenericServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
		} catch (Throwable e) {
			throw new ExceptionInInitializerError(e); 
		}
	}
	
	public Session getSession() throws HibernateException {
		return sessionFactory.openSession();
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
}

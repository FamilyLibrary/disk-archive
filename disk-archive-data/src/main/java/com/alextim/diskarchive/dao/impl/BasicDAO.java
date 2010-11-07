package com.alextim.diskarchive.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alextim.diskarchive.dao.IBasicDAO;

public class BasicDAO<T> extends HibernateDaoSupport implements IBasicDAO<T>{

	private Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public BasicDAO(){
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> findAll() {
		Collection<T> entities = getHibernateTemplate().executeFind(new HibernateCallback() {
			@Override
			public Collection<T> doInHibernate(Session session) throws HibernateException,	SQLException {
				Query query = session.createQuery("from " + persistentClass.getSimpleName()); 
				return query.list();
			}
		});
		return entities;
	}

}

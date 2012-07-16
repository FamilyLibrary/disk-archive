package com.alextim.diskarchive.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IBasicDAO;

@Transactional
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

	protected void saveOrUpdate(T object) {
		getHibernateTemplate().saveOrUpdate(object);
	}

	@Override
	public void delete(T object) {
		getHibernateTemplate().delete(object);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Long id) {
		T object = (T)getHibernateTemplate().get(this.persistentClass, id);
		return object;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getFirst() {
		return (T)getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,	SQLException {
				Query query = session.createQuery("from " + persistentClass.getSimpleName() + " order by id");
				List<T> groups = query.list();
				T result = null;
				if (groups.size() > 0) {
					result = groups.get(0);
				}
				return result;
			}
		});
	}

}

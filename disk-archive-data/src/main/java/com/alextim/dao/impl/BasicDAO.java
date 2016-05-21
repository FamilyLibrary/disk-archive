package com.alextim.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.IBasicDAO;

@Transactional
public class BasicDAO<T> implements IBasicDAO<T>{
    @Autowired
    private HibernateTemplate hibernateTemplate;

    private Class<T> persistentClass;

    public BasicDAO(Class<T> persistentClass){
        //TODO Remove or replace persistentClass field to something
        //this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.persistentClass = persistentClass;
    }

    @SuppressWarnings("unchecked")
    public BasicDAO(){
        //TODO Remove or replace persistentClass field to something
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    @SuppressWarnings("unchecked")
    public Collection<T> findAll() {
        Collection<T> entities = getHibernateTemplate().execute(new HibernateCallback<Collection<T>>() {
            @Override
            public Collection<T> doInHibernate(Session session) throws HibernateException {
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
    
    @Override
    public void getByIdThenDelete(final Long id) {
        final T entity = getById(id);
        if (entity != null) {
            getHibernateTemplate().delete(entity);
        }
    }

    @Override
    public T getById(Long id) {
        T object = getHibernateTemplate().get(this.persistentClass, id);
        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getFirst() {
        return getHibernateTemplate().execute(new HibernateCallback<T>() {
            @Override
            public T doInHibernate(Session session) throws HibernateException {
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

    protected HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    protected SessionFactory getSessionFactory() {
        return hibernateTemplate.getSessionFactory();
    }
}

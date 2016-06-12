package com.alextim.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.IBasicDAO;

@Transactional
public class BasicDAO<T> implements IBasicDAO<T>{

    @PersistenceContext
    protected EntityManager entityManager;

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
    	final Query query = entityManager.unwrap(Session.class).createQuery("from " + persistentClass.getSimpleName());
        return query.list();
    }

    protected void saveOrUpdate(T object) {
        entityManager.unwrap(Session.class).saveOrUpdate(object);
    }

    @Override
    public void delete(T object) {
    	entityManager.unwrap(Session.class).delete(object);
    }
    
    @Override
    public void getByIdThenDelete(final Long id) {
        final T entity = getById(id);
        if (entity != null) {
        	delete(entity);
        }
    }

    @Override
    public T getById(Long id) {
        T object = entityManager.unwrap(Session.class).get(this.persistentClass, id);
        return object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getFirst() {
    	final Query query = entityManager.unwrap(Session.class).createQuery("from " + persistentClass.getSimpleName());
        List<T> groups = query.list();
        T result = null;
        if (groups.size() > 0) {
            result = groups.get(0);
        }
        return result;
    }
}

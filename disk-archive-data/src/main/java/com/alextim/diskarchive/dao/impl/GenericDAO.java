package com.alextim.diskarchive.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.diskarchive.entity.IEntity;

@Repository
public class GenericDAO implements IGenericDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

	@Override
	public Object getById(Class<? extends IEntity> clazz, Long id) {
		return hibernateTemplate.get(clazz, id);
	}

}

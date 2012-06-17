package com.alextim.diskarchive.dao.impl;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.diskarchive.entity.IEntity;

public class GenericDAO extends HibernateDaoSupport implements IGenericDAO{

	@Override
	public Object getById(Class<? extends IEntity> clazz, Long id) {
		return getHibernateTemplate().get(clazz, id);
	}

}

package com.alextim.diskarchive.dao.impl;

import org.springframework.stereotype.Repository;

import com.alextim.dao.impl.BasicDAO;
import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.entity.IEntity;

@Repository
@Deprecated
public class GenericDAO extends BasicDAO<IEntity> implements IGenericDAO {

	@Override
	public Object getById(Class<? extends IEntity> clazz, Long id) {
		return getById(clazz, id);
	}

}

package com.alextim.diskarchive.dao;

import com.alextim.entity.IEntity;

public interface IGenericDAO {
	public Object getById(Class<? extends IEntity> clazz, Long id);
}

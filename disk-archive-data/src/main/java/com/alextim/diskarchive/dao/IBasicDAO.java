package com.alextim.diskarchive.dao;

import java.util.Collection;

public interface IBasicDAO<T> {
	public Collection<T> findAll();
	
	public T getById(Long id);
	public void delete(T object);
	public void saveOrUpdate(T object);
	
	public T getFirst();
}

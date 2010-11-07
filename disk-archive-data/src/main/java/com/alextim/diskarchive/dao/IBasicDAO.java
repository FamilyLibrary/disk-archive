package com.alextim.diskarchive.dao;

import java.util.Collection;

public interface IBasicDAO<T> {
	public Collection<T> findAll();
}

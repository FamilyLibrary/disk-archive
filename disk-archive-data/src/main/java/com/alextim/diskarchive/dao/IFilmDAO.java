package com.alextim.diskarchive.dao;

import java.util.Collection;

import com.alextim.diskarchive.entity.Film;

public interface IFilmDAO {
	public Collection<Film> findAll();
}

package com.alextim.diskarchive.dao.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.entity.Film;

public class FilmDAO extends HibernateDaoSupport implements IFilmDAO {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Film> findAll() {
		Collection<Film> films = new ArrayList<Film>();
		films.addAll(getHibernateTemplate().find("from Film"));

		return films;
	}
	
}

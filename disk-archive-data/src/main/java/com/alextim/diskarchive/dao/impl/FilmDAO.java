package com.alextim.diskarchive.dao.impl;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.entity.Film;

@Transactional
public class FilmDAO extends BasicDAO<Film> implements IFilmDAO {

	@Override
	public void addFilm(Film film) {
		saveOrUpdate(film);
	}

	@Override
	public void addFilm() {
		Film film = new Film();
		
		film.setDescription("");
		film.setName("");
		
		addFilm(film);
	}
	
}

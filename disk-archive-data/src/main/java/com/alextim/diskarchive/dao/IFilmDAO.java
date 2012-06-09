package com.alextim.diskarchive.dao;

import com.alextim.diskarchive.entity.Film;


public interface IFilmDAO extends IBasicDAO<Film>{

	void addFilm(Film film);
	void addFilm();

}

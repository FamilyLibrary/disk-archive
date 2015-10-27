package com.alextim.diskarchive.dao;

import com.alextim.diskarchive.entity.Film;
import com.alextim.general.dao.IBasicDAO;


public interface IFilmDAO extends IBasicDAO<Film>{

	void addFilm(Film film);
	Film addFilm();

	Film findByName(String name);
}

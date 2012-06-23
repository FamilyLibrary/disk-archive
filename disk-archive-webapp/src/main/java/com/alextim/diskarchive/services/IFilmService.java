package com.alextim.diskarchive.services;

import java.util.List;

import com.alextim.diskarchive.FilmInfoWrapper;
import com.alextim.diskarchive.entity.Film;

public interface IFilmService {
	List<Film> getFilms();

	Film getById(Long filmId);

	void addFilm();
	void deleteFilm(Long id);
	
	void save(String jsonResult);
	
	FilmInfoWrapper filmInfo(Long id);

	String convertToJSON(List<Film> films);
}

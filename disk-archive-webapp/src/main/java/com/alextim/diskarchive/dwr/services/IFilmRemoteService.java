package com.alextim.diskarchive.dwr.services;

import java.util.List;

import com.alextim.diskarchive.FilmInfoWrapper;
import com.alextim.diskarchive.entity.Film;

public interface IFilmRemoteService {
	Film addFilm();
	void deleteFilm(Long id);
	
	void save(String jsonResult);
	
	FilmInfoWrapper filmInfo(Long id);

	List<Film> getFilms();
}

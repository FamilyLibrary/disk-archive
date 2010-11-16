package com.alextim.diskarchive.dwr.services;

import com.alextim.diskarchive.FilmInfoWrapper;

public interface IFilmRemoteService {
	void addFilm();
	void deleteFilm(Long id);
	
	void save(String jsonResult);
	
	FilmInfoWrapper filmInfo(Long id);
}

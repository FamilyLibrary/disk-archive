package com.alextim.diskarchive.dwr.services;

public interface IFilmRemoteService {
	void addFilm();
	void deleteFilm(Long id);
	
	void save(String jsonResult);
}

package com.alextim.diskarchive.dwr.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.alextim.diskarchive.FilmInfoWrapper;
import com.alextim.diskarchive.dwr.services.IFilmRemoteService;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.services.IFilmService;

public class FilmRemoteService implements IFilmRemoteService{

	private IFilmService filmService;
	
    @Override
	public Film addFilm() {
		return filmService.addFilm();
	}
	
	@Override
	public void deleteFilm(Long id) {
		filmService.deleteFilm(id);	
	}
	
	@Override
	public void save(String jsonResult) {
		filmService.save(jsonResult);
	}
	
	@Override
	public FilmInfoWrapper filmInfo(Long id) {
		return filmService.filmInfo(id);
	}

    public List<Film> getFilms() {
        return filmService.getFilms();
    }

    public void setFilmService(IFilmService filmService) {
        this.filmService = filmService;
    }
}

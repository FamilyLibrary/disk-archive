package com.alextim.diskarchive.dwr.services.impl;

import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.dwr.services.IFilmRemoteService;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;

public class FilmRemoteService implements IFilmRemoteService{

	private CoreDAOFactory coreDAOFactory;

	public FilmRemoteService(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	@Override
	public void addFilm() {
		FilmGroup filmGroup = coreDAOFactory.getFilmGroupDAO().getFirst();
		
		Film film = new Film();
		
		film.setAuthor(null);
		film.setDescription("");
		film.setFilmGroup(filmGroup);
		film.setName("");
	
		coreDAOFactory.getFilmDAO().saveOrUpdate(film);
	}

	@Override
	public void deleteFilm(Long id) {
		Film film = coreDAOFactory.getFilmDAO().getById(id);
		coreDAOFactory.getFilmDAO().delete(film);
		
	}

}

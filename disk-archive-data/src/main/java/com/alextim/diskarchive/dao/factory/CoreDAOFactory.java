package com.alextim.diskarchive.dao.factory;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;

public class CoreDAOFactory {
	private static CoreDAOFactory factory;
	
	private IFilmGroupDAO filmGroupDAO;
	private IFilmDAO filmDAO;

	public IFilmGroupDAO getFilmGroupDAO() {
		return filmGroupDAO;
	}
	public void setFilmGroupDAO(IFilmGroupDAO filmGroupDAO) {
		this.filmGroupDAO = filmGroupDAO;
	}

	public IFilmDAO getFilmDAO() {
		return filmDAO;
	}
	public void setFilmDAO(IFilmDAO filmDAO) {
		this.filmDAO = filmDAO;
	}

	public static CoreDAOFactory getInstance() {
		if (factory == null) {
			factory = new CoreDAOFactory();
		}
		return factory;
	}
}

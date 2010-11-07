package com.alextim.diskarchive.dao.factory;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.ISeriesDAO;

public class CoreDAOFactory {
	private IFilmGroupDAO filmGroupDAO;
	private IFilmDAO filmDAO;
	private ISeriesDAO seriesDAO;

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

	public ISeriesDAO getSeriesDAO() {
		return seriesDAO;
	}
	public void setSeriesDAO(ISeriesDAO seriesDAO) {
		this.seriesDAO = seriesDAO;
	}
}

package com.alextim.diskarchive.dao.factory;

import com.alextim.diskarchive.dao.IAuthorDAO;
import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.diskarchive.dao.ISeriesDAO;

public class CoreDAOFactory {
	private IFilmGroupDAO filmGroupDAO;
	private IFilmDAO filmDAO;
	private ISeriesDAO seriesDAO;
	private IAuthorDAO authorDAO;
	private IGenericDAO genericDAO;

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
	
	public IAuthorDAO getAuthorDAO() {
		return authorDAO;
	}
	public void setAuthorDAO(IAuthorDAO authorDAO) {
		this.authorDAO = authorDAO;
	}
	
	public IGenericDAO getGenericDAO() {
		return genericDAO;
	}
	public void setGenericDAO(IGenericDAO genericDAO) {
		this.genericDAO = genericDAO;
	}
}

package com.alextim.diskarchive.dao.factory;

import com.alextim.diskarchive.dao.IAuthorDAO;
import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.IGenericDAO;
import com.alextim.diskarchive.dao.ISeriesDAO;

public interface ICoreDAOFactory {
	IFilmGroupDAO getFilmGroupDAO();
	IFilmDAO getFilmDAO();
	ISeriesDAO getSeriesDAO();
	IAuthorDAO getAuthorDAO();
	IGenericDAO getGenericDAO();
}

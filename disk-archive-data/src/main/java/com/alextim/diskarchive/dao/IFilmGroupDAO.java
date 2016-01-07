package com.alextim.diskarchive.dao;

import com.alextim.dao.IBasicDAO;
import com.alextim.diskarchive.entity.FilmGroup;


public interface IFilmGroupDAO extends IBasicDAO<FilmGroup>{
	void addGroup();
	void addGroup(FilmGroup filmGroup);
}

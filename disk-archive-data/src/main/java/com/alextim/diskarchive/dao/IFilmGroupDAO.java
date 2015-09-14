package com.alextim.diskarchive.dao;

import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.general.dao.IBasicDAO;


public interface IFilmGroupDAO extends IBasicDAO<FilmGroup>{
	void addGroup();
	void addGroup(FilmGroup filmGroup);
}

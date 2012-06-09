package com.alextim.diskarchive.services;

import java.util.List;

import com.alextim.diskarchive.entity.FilmGroup;

public interface IFilmGroupService {

	List<FilmGroup> getFilmGroups();
	void addGroup();

}

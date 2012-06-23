package com.alextim.diskarchive.services;

import java.util.Comparator;
import java.util.List;

import com.alextim.diskarchive.entity.FilmGroup;

public interface IFilmGroupService {

    List<FilmGroup> getFilmGroups(Comparator<FilmGroup> comporator);
	void addGroup();
	
    void save(String jsonResult);
    String convertToJSON(List<FilmGroup> groups);

}

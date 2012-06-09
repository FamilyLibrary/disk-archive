package com.alextim.diskarchive.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;

@Transactional
public class FilmGroupServiceImpl implements IFilmGroupService {
	private CoreDAOFactory coreDAOFactory;

	public FilmGroupServiceImpl(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	@Override
	public List<FilmGroup> getFilmGroups() {
		List<FilmGroup> groups = new ArrayList<FilmGroup>(); 
		groups.addAll(coreDAOFactory.getFilmGroupDAO().findAll());
		return groups;
	}
	
	@Override
	public void addGroup() {
		IFilmGroupDAO groupDao = coreDAOFactory.getFilmGroupDAO();
		groupDao.addGroup();
	}

}

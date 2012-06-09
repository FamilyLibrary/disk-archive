package com.alextim.diskarchive.dwr.services.impl;

import com.alextim.diskarchive.dwr.services.IFilmGroupRemoteService;
import com.alextim.diskarchive.services.IFilmGroupService;

public class FilmGroupRemoteService implements IFilmGroupRemoteService{

	private IFilmGroupService filmGroupService;

	@Override
	public void addGroup() {
		filmGroupService.addGroup();
	}
	
	public IFilmGroupService getFilmGroupService() {
		return filmGroupService;
	}
	public void setFilmGroupService(IFilmGroupService filmGroupService) {
		this.filmGroupService = filmGroupService;
	}

}

package com.alextim.diskarchive.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.services.impl.FilmGroupServiceImpl;

public class FilmGroupController extends MultiActionController {
	IFilmGroupService filmGroupService;
	

	public ModelAndView filmGroup(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/filmGroup.jsp");

		List<FilmGroup> groups = filmGroupService.getFilmGroups(FilmGroupServiceImpl.BY_GROUPNAME);
		String rows = filmGroupService.convertToJSON(groups);
		
		mv.addObject("title", "Film Groups");
		mv.addObject("rows", rows);
		return mv;
	}
	
	public IFilmGroupService getFilmGroupService() {
		return filmGroupService;
	}
	public void setFilmGroupService(IFilmGroupService filmGroupService) {
		this.filmGroupService = filmGroupService;
	}

}

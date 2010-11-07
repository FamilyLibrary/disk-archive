package com.alextim.diskarchive.controllers;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;

public class IndexController extends MultiActionController{
	private final CoreDAOFactory coreDAOFactory;
	
	public IndexController(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/login.jsp");
		mv.addObject("title", "Login");
		return mv;
	}
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/main.jsp");
	
		IFilmGroupDAO filmGroupDAO = coreDAOFactory.getFilmGroupDAO();
		IFilmDAO filmDAO = coreDAOFactory.getFilmDAO();
		
		Collection<FilmGroup> filmGroups = filmGroupDAO.findAll();
		Collection<Film> films = filmDAO.findAll();

		String rows = "[";
		for (Iterator<Film> iterator = films.iterator(); iterator.hasNext(); ) {
			Film film = iterator.next();
			
			Long id = film.getId();
			String filmName = film.getName();
			FilmGroup filmGroup = film.getFilmGroup();
			String description = film.getDescription();
			
			String row = "{";
			row+="id: " + id;
			row+=",filmName: '" + filmName + "'";
			row+=",filmGroupId: " + (filmGroup == null ? -1L : filmGroup.getId());
			row+=",description: '" + description + "'";
			row+="}";

			if (iterator.hasNext()) {
				row+=",";
			}
			rows+=row;
		}
		rows+="]";
		
		mv.addObject("title", "Films");
		mv.addObject("filmGroups", filmGroups);
		mv.addObject("films", films);
		mv.addObject("rows", rows);
		
		return mv;
	}
}

package com.alextim.diskarchive.controllers;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.entity.Film;

public class IndexController extends MultiActionController{
	private final CoreDAOFactory coreDAOFactory;
	
	public IndexController(CoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}
	
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/login.jsp");
		return mv;
	}
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/main.jsp");
	
		IFilmDAO filmDAO = coreDAOFactory.getFilmDAO();
		Collection<Film> films = filmDAO.findAll();
		
		mv.addObject("films", films);
		
		return mv;
	}
}

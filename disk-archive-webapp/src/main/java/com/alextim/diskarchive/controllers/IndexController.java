package com.alextim.diskarchive.controllers;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.directwebremoting.util.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.entity.IEntity;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.services.IFilmService;
import com.alextim.diskarchive.utils.JSONHelper;

public class IndexController extends MultiActionController {
	private final Logger log = Logger.getLogger(IndexController.class);
	
	private IFilmService filmService;
	private IFilmGroupService filmGroupService;

	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/login.jsp");
		mv.addObject("title", "Login");
		return mv;
	}

	public ModelAndView renderGeneralImage(HttpServletRequest request, HttpServletResponse response) {
		String filmIdParam = request.getParameter("filmId");
		Long filmId = Long.parseLong(filmIdParam);

		Film film = filmService.getById(filmId);
		byte[] imageArray = film.getImage();

		if (imageArray != null) {
			try {
				response.getOutputStream().write(imageArray);
				response.setContentType("application/octet-stream");
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					response.getOutputStream().close();
				} catch (IOException e) {
				}
			}
		}
		return null;
	}

	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/main.jsp");
		log.info("Start uploading main page...");

		List<FilmGroup> filmGroups = filmGroupService.getFilmGroups();
		List<? extends IEntity> films = filmService.getFilms();

		Collections.sort(filmGroups, new Comparator<FilmGroup>() {
			@Override
			public int compare(FilmGroup filmGroup1, FilmGroup filmGroup2) {
				String name1 = filmGroup1.getName();
				String name2 = filmGroup2.getName();

				int result = 0;
				if (name1 != null) {
					result = name1.compareTo(name2);
				} else if (name2 != null) {
					result = name2.compareTo(name1);
				}
				return result;
			}
		});

		String rows = JSONHelper.convertToJSON(films);
		
		mv.addObject("title", "Films");
		mv.addObject("filmGroups", filmGroups);
		mv.addObject("films", films);
		mv.addObject("rows", rows);

		log.info("Uploaded main page...");
		return mv;
	}

	public IFilmService getFilmService() {
		return filmService;
	}
	public void setFilmService(IFilmService filmService) {
		this.filmService = filmService;
	}

	public IFilmGroupService getFilmGroupService() {
		return filmGroupService;
	}
	public void setFilmGroupService(IFilmGroupService filmGroupService) {
		this.filmGroupService = filmGroupService;
	}

}

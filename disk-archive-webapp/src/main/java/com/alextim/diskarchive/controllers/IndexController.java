package com.alextim.diskarchive.controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.json.JsonView;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.dao.factory.CoreDAOFactory;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;

public class IndexController extends MultiActionController{
	private final CoreDAOFactory coreDAOFactory;
	private JsonView jsonView;
	
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
		
		List<FilmGroup> filmGroups = new ArrayList<FilmGroup>(filmGroupDAO.findAll());
		Collection<Film> films = filmDAO.findAll();

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
		
		JsonConfig config = new JsonConfig();
		config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		
		String rows = "[";
		for (Iterator<Film> iterator = films.iterator(); iterator.hasNext(); ) {
			Film film = iterator.next();
			
			JSONObject obj = (JSONObject)JSONSerializer.toJSON(film, config);
			String row = obj.toString();
			
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

	public JsonView getJsonView() {
		return jsonView;
	}
	public void setJsonView(JsonView jsonView) {
		this.jsonView = jsonView;
	}
	
}

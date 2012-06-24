package com.alextim.diskarchive.controllers;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.alextim.diskarchive.FilmFileUploadBean;
import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.Film;

public class FileUploadController extends SimpleFormController {
	private ICoreDAOFactory coreDAOFactory;

	public FileUploadController(ICoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/uploadFile.jsp");

		FilmFileUploadBean bean = (FilmFileUploadBean) command;

		Film film = bean.getFilm();
		MultipartFile file = bean.getFile();

		BufferedInputStream fs = null;
		try {
			fs = new BufferedInputStream(file.getInputStream());
			{
				byte[] imageArray = new byte[fs.available()];
				fs.read(imageArray);

				film.setImage(imageArray);
			}
			coreDAOFactory.getFilmDAO().saveOrUpdate(film);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fs != null) {
				fs.close();
			}
		}
		return mv;
	}

	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}

	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		FilmFileUploadBean bean = new FilmFileUploadBean();

		String filmIdParam = request.getParameter("filmId");
		try {
			Long filmId = Long.parseLong(filmIdParam);

			Film film = coreDAOFactory.getFilmDAO().getById(filmId);
			bean.setFilm(film);
			
			request.setAttribute("filmId", film.getId());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return bean;
	}
}

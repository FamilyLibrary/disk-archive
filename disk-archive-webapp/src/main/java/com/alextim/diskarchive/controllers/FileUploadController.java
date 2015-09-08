package com.alextim.diskarchive.controllers;

import java.io.BufferedInputStream;
import java.io.IOException;

import javax.servlet.ServletException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.alextim.diskarchive.FilmFileUploadBean;
import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.Film;

@Controller
@RequestMapping("/uploadFile.html")
public class FileUploadController {
	private ICoreDAOFactory coreDAOFactory;

	public FileUploadController() {
		
	}
	public FileUploadController(ICoreDAOFactory coreDAOFactory) {
		this.coreDAOFactory = coreDAOFactory;
	}

	@RequestMapping(method=RequestMethod.POST)
	protected ModelAndView onSubmit(@ModelAttribute("command") FilmFileUploadBean bean, BindException errors)
			throws Exception {
		ModelAndView mv = new ModelAndView("WEB-INF/jsp/uploadFile.jsp");

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
			coreDAOFactory.getFilmDAO().addFilm(film);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fs != null) {
				fs.close();
			}
		}
		return mv;
	}

	@RequestMapping(method=RequestMethod.GET)
    protected Object initForm(ModelMap model)
            throws Exception {
        FilmFileUploadBean bean = new FilmFileUploadBean();

        String filmIdParam = (String)model.get("filmId");
        try {
            Long filmId = Long.parseLong(filmIdParam);

            Film film = coreDAOFactory.getFilmDAO().getById(filmId);
            bean.setFilm(film);

            model.addAttribute("filmId", film.getId());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return bean;
    }

	@InitBinder
	protected void initBinder(WebDataBinder binder) throws ServletException {
		binder.registerCustomEditor(byte[].class,
				new ByteArrayMultipartFileEditor());
	}
}

package com.alextim.diskarchive.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.entity.FilmGroup;
import com.alextim.diskarchive.services.IFilmGroupService;
import com.alextim.diskarchive.services.IFilmService;
import com.alextim.diskarchive.services.impl.FilmGroupServiceImpl;

@Controller
@RequestMapping("/main.html")
public class MainController extends MultiActionController {
    private final Logger LOG = Logger.getLogger(MainController.class);

    private final String IMAGE_NAME = "/images/nophoto-thumb.gif";

    @Resource
    private IFilmService filmService;
    @Resource
    private IFilmGroupService filmGroupService;

    @RequestMapping(value="/renderGeneralImage.html", method=GET)
    public ModelAndView renderGeneralImage(HttpServletRequest request,
            HttpServletResponse response) {
        String filmIdParam = request.getParameter("filmId");
        Long filmId = Long.parseLong(filmIdParam);

        Film film = filmService.getById(filmId);
        byte[] imageArray = film.getImage();

        try {
            if (ArrayUtils.isEmpty(imageArray)) {
                InputStream stream = getClass().getResourceAsStream(IMAGE_NAME);
                int size = stream.available();

                imageArray = new byte[size];
                
                stream.read(imageArray, 0, size);
                stream.close();
            }
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

        return null;
    }

    @RequestMapping(method=GET)
    public ModelAndView main(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("WEB-INF/jsp/main.jsp");
        LOG.info("Start uploading main page...");

        List<FilmGroup> filmGroups = filmGroupService
                .getFilmGroups(FilmGroupServiceImpl.BY_GROUPNAME);
        List<Film> films = filmService.getFilms();

        String rows = this.filmService.convertToJSON(films);

        mv.addObject("title", "Films");
        mv.addObject("filmGroups", filmGroups);
        mv.addObject("films", films);
        mv.addObject("rows", rows);

        LOG.info("Uploaded main page...");
        return mv;
    }
}

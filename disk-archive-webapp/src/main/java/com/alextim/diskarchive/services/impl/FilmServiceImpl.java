package com.alextim.diskarchive.services.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.util.PropertyFilter;

import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.FilmInfoWrapper;
import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.dao.factory.ICoreDAOFactory;
import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.services.IFilmService;
import com.alextim.diskarchive.utils.JSONHelper;

@Transactional
public class FilmServiceImpl implements IFilmService {
    private ICoreDAOFactory coreDAOFactory;
    private JSONHelper jsonHelper;

    public FilmServiceImpl(ICoreDAOFactory coreDAOFactory) {
        this.coreDAOFactory = coreDAOFactory;
    }

    @Override
    public List<Film> getFilms() {
        List<Film> films = new ArrayList<Film>();
        films.addAll(coreDAOFactory.getFilmDAO().findAll());
        return films;
    }

    @Override
    public Film getById(Long filmId) {
        return coreDAOFactory.getFilmDAO().getById(filmId);
    }

    public void addFilm() {
        coreDAOFactory.getFilmDAO().addFilm();
    }

    @Override
    public void deleteFilm(Long id) {
        Film film = coreDAOFactory.getFilmDAO().getById(id);
        coreDAOFactory.getFilmDAO().delete(film);
    }

    @Override
    public FilmInfoWrapper filmInfo(Long id) {
        Film film = coreDAOFactory.getFilmDAO().getById(id);
        FilmInfoWrapper wrapper = new FilmInfoWrapper(film);
        return wrapper;
    }

    @Override
    public String convertToJSON(List<Film> films) {
        PropertyFilter filter = new PropertyFilter() {
            public boolean apply(Object source, String name, Object value) {
                if (source instanceof Film && "image".equals(name)) {
                    return true;
                }
                return false;
            }
        };
        return jsonHelper.json(films, filter);
    }

    @Override
    public void save(String jsonResult) {
        IFilmDAO dao = this.coreDAOFactory.getFilmDAO();
        Film film = jsonHelper.unjson(dao, jsonResult);
        
        coreDAOFactory.getFilmDAO().addFilm(film);
    }

    public JSONHelper getJsonHelper() {
        return jsonHelper;
    }
    public void setJsonHelper(JSONHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

}

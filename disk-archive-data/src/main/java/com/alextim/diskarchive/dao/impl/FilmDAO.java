package com.alextim.diskarchive.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.entity.Film;

@Transactional
public class FilmDAO extends BasicDAO<Film> implements IFilmDAO {

	@Override
	public void addFilm(Film film) {
		saveOrUpdate(film);
	}

	@Override
	public void addFilm() {
		Film film = new Film();
		
		film.setDescription("");
		film.setName("");
		
		addFilm(film);
	}

    @SuppressWarnings("unchecked")
    @Override
    public Film findByName(String name) {
        Film result = null;
        
        List<Film> resultList = getSession().createCriteria(Film.class)
            .add(Restrictions.eq("name", name))
            .list();
        
        if (resultList != null && resultList.size() > 0) {
            result = resultList.get(0);
        }
        return result;
    }
}

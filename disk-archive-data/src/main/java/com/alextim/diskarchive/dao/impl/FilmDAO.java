package com.alextim.diskarchive.dao.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.impl.BasicDAO;
import com.alextim.diskarchive.dao.IFilmDAO;
import com.alextim.diskarchive.entity.Film;

@Repository
@Transactional
public class FilmDAO extends BasicDAO<Film> implements IFilmDAO {

	@Override
	public void addFilm(Film film) {
		saveOrUpdate(film);
	}

	@Override
	public Film addFilm() {
        final Film film = new Film();

        film.setDescription("");
        film.setName("");

        addFilm(film);

        return film;
	}

    @SuppressWarnings("unchecked")
    @Override
    public Film findByName(String name) {
        Film result = null;

        List<Film> resultList = getSessionFactory().getCurrentSession().createCriteria(Film.class)
            .add(Restrictions.eq("name", name))
            .list();
        
        if (resultList != null && resultList.size() > 0) {
            result = resultList.get(0);
        }
        return result;
    }
}

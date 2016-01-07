package com.alextim.diskarchive.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.dao.impl.BasicDAO;
import com.alextim.diskarchive.dao.IFilmGroupDAO;
import com.alextim.diskarchive.entity.FilmGroup;

@Repository
@Transactional
public class FilmGroupDAO extends BasicDAO<FilmGroup> implements IFilmGroupDAO {

    @Override
    public void addGroup(FilmGroup filmGroup) {
        saveOrUpdate(filmGroup);
    }

    @Override
    public void addGroup() {
        final FilmGroup group = new FilmGroup();

        group.setName(FilmGroup.NEW_NAME);
        group.setDescription(FilmGroup.NEW_DESCRIPTION);

        addGroup(group);
    }

}

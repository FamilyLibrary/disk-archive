package com.alextim.diskarchive.dwr.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.diskarchive.entity.Film;
import com.alextim.diskarchive.services.IFilmService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:hibernate.xml",
        "file:src/main/webapp/WEB-INF/web-application-config.xml" }, inheritLocations = true)
@Transactional
public class FilmRemoteServiceTest {
    public static final Logger LOG = Logger.getLogger(FilmRemoteServiceTest.class);

    @Resource
    private IFilmService filmService;

    private FilmRemoteService filmRemoteService = new FilmRemoteService();

    private Long filmId;

    @Before
    public void setUp() {
        filmRemoteService.setFilmService(filmService);
    }

    @Test
    @Rollback(true)
    public void testAddFilm() {
        final Film created = filmRemoteService.addFilm();
        Assert.assertNotNull(created);

        filmId = created.getId();

        List<Film> films = filmRemoteService.getFilms();
        List<Film> filtered = 
                films.stream().filter(film -> film.getId().equals(filmId)).collect(Collectors.toList());

        Assert.assertEquals(filtered.size(), 1);
    }
}

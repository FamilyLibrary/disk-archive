package com.alextim.diskarchive;

import com.alextim.diskarchive.entity.Film;


public class FilmInfoWrapper {
	private static final String FILM_PARAM = "filmId";

	private static String baseImageURL = "renderGeneralImage.html";
	
	private transient Film film;

	public FilmInfoWrapper(Film film) {
		this.film = film;
	}
	
	public Long getFilmId() {
		return film.getId();
	}
	
	public String getAuthor() {
		String author = "";
		if (film.getAuthor() != null) {
			author = film.getAuthor().getName();
		}
		return author;
	}

	public String getDescription() {
		return film.getDescription();
	}
	
	public int getNumberOfSeries() {
		return 0;
	}
	
	public String getImageUrl() {
		return baseImageURL + "?" + FILM_PARAM + "=" + film.getId();
	}
}

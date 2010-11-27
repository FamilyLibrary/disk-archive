package com.alextim.diskarchive;

import com.alextim.diskarchive.entity.Film;

public class FilmFileUploadBean extends FileUploadBean{
	private Film film;

	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
}

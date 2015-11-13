package com.alextim.diskarchive.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.general.entity.Person;

@Entity
@Table(name="AUTHORS")
@PrimaryKeyJoinColumn(name="ID")
public class Author extends Person {
    @OneToOne(mappedBy="author")
	private Film film;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="FILMS_AUTHORS",
		joinColumns=@JoinColumn(name="author_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private Set<Film> films;

	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
	}
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
}

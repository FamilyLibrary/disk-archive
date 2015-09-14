package com.alextim.diskarchive.entity;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="authors")
public class Author implements IEntity{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne(mappedBy="author")
	private Film film;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="films_authors",
		joinColumns=@JoinColumn(name="author_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private Set<Film> films;

	@Column(name="author")
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
}

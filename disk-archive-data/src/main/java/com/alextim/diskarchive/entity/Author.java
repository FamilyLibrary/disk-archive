package com.alextim.diskarchive.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="film_authors")
public class Author{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
	@JoinColumn(name="film_id")
	private Film film;
	
	/*@OneToOne
	@JoinTable(
		name="films_authors",
		joinColumns=@JoinColumn(name="author_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private Film film;*/
	
	@Basic
	@Column(name="author")
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

package com.alextim.diskarchive.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="actors")
public class Actor {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="films_actors",
		joinColumns=@JoinColumn(name="actor_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private Set<Film> films;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
	}
}

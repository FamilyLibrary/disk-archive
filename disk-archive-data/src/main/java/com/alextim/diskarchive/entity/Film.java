package com.alextim.diskarchive.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="films")
public class Film {
	@Id
	private Long id;
	private String name;
	
	@OneToOne
	@PrimaryKeyJoinColumn
	private FilmGroup filmGroup;
	
	public FilmGroup getFilmGroup() {
		return filmGroup;
	}
	public void setFilmGroup(FilmGroup filmGroup) {
		this.filmGroup = filmGroup;
	}
	
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
}

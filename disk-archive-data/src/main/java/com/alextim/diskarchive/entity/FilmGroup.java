package com.alextim.diskarchive.entity;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Entity;

@Entity
@Table(name="film_groups")
public class FilmGroup {
	@Id
	private Long id;
	private String name;
	
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

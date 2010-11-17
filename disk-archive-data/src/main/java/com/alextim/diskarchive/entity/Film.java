package com.alextim.diskarchive.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="films")
public class Film  implements IEntity{
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String name;
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="group_id")
	private FilmGroup filmGroup;
	
	@OneToOne(mappedBy="film")
	private Author author;
	
	//@OneToMany(fetch=FetchType.EAGER, mappedBy="film")
	@OneToMany(fetch=FetchType.EAGER)
	@JoinColumn(name="film_id", insertable=false, updatable=false)
	private Set<Actor> actors;
	
	public FilmGroup getFilmGroup() {
		if (filmGroup == null) {
			FilmGroup defaultGroup = new FilmGroup();
			defaultGroup.setId(-1L);
			defaultGroup.setName("");
			defaultGroup.setDescription("");
			
			return defaultGroup; 
		}
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
	
	public String getDescription() {
		return description == null ? "" : description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Author getAuthor() {
		if (author == null) {
			Author defaultAuthor = new Author();
			defaultAuthor.setId(-1L);
			defaultAuthor.setName("");
			defaultAuthor.setFilm(this);
			
			return defaultAuthor; 
		}
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	
	public Set<Actor> getActors() {
		return actors;
	}
	public void setActors(Set<Actor> actors) {
		this.actors = actors;
	}
}

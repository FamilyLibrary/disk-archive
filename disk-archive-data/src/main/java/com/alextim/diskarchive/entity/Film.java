package com.alextim.diskarchive.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alextim.entity.IEntity;

@Entity
@Table(name="FILMS")
public class Film  implements IEntity{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FILMS_SEQUENCE")
	@SequenceGenerator(name="FILMS_SEQUENCE", sequenceName="SEQ_FILMS")
    @Column(name="ID")
	private Long id;

	private String name;
	private String description;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="group_id")
	private FilmGroup filmGroup;
	
	@OneToOne
	@JoinColumn(name="FILM_ID")
	private Author author;
	
	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="FILMS_ACTORS",
		joinColumns=@JoinColumn(name="film_id"),
		inverseJoinColumns=@JoinColumn(name="actor_id")
	)
	private Set<Actor> actors;

	@Lob
	private byte[] image;
	
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

	@Override
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
			defaultAuthor.setFirstName("");
			defaultAuthor.setLastName("");
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
	
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}

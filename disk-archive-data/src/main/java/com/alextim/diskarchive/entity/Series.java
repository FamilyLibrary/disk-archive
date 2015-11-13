package com.alextim.diskarchive.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="SERIES")
public class Series implements IEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SERIES_SEQUENCE")
    @SequenceGenerator(name="SERIES_SEQUENCE", sequenceName="SEQ_SERIES")
    @Column(name="ID")
	private Long id;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="film_id")
	private Film film;

	private String name;
	private String description;
	
	@Lob
	private Byte[] image;

	@Override
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Byte[] getImage() {
		return image;
	}
	public void setImage(Byte[] image) {
		this.image = image;
	}
}

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.alextim.entity.IEntity;

@Entity
@Table(name="ACTORS")
public class Actor implements IEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACTORS_SEQUENCE")
    @SequenceGenerator(name="ACTORS_SEQUENCE", sequenceName="SEQ_ACTORS")
    @Column(name="ID")
	private Long id;

    private String name;

	@OneToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name="FILMS_ACTORS",
		joinColumns=@JoinColumn(name="actor_id"),
		inverseJoinColumns=@JoinColumn(name="film_id")
	)
	private Set<Film> films;
	
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
	
	public Set<Film> getFilms() {
		return films;
	}
	public void setFilms(Set<Film> films) {
		this.films = films;
	}
}

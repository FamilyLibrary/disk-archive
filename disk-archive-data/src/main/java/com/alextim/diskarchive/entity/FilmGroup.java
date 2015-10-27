package com.alextim.diskarchive.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.general.entity.Group;

@Entity
@Table(name="FILM_GROUPS")
@PrimaryKeyJoinColumn(name="ID")
public class FilmGroup extends Group {

}

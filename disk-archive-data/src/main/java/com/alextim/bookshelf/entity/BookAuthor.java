package com.alextim.bookshelf.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.general.entity.Person;

@Entity
@Table(name="BOOK_AUTHORS")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class BookAuthor extends Person {

}

package com.alextim.bookshelf.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.entity.Person;

@Entity
@Table(name="BOOK_AUTHORS")
@PrimaryKeyJoinColumn(name="ID")
public class BookAuthor extends Person {
}

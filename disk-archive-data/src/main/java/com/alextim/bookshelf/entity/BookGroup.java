package com.alextim.bookshelf.entity;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.general.entity.Group;

@Entity
@Table(name="BOOK_GROUP")
@Inheritance(strategy=InheritanceType.JOINED)
@PrimaryKeyJoinColumn(name="ID")
public class BookGroup extends Group {

}
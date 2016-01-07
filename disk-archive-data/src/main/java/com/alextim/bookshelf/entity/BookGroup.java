package com.alextim.bookshelf.entity;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.alextim.entity.Group;

@Entity
@Table(name="BOOK_GROUPS")
@PrimaryKeyJoinColumn(name="ID")
public class BookGroup extends Group {

}
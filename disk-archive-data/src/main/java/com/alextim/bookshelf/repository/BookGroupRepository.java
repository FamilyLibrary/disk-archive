package com.alextim.bookshelf.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alextim.bookshelf.entity.BookGroup;

public interface BookGroupRepository extends JpaRepository<BookGroup, Serializable>, BookGroupCustom {

}

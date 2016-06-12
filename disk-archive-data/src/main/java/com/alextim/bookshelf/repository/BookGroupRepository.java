package com.alextim.bookshelf.repository;

import org.springframework.data.repository.CrudRepository;

import com.alextim.bookshelf.entity.BookGroup;

public interface BookGroupRepository extends CrudRepository<BookGroup, Long>{

}

package com.alextim.bookshelf.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.alextim.bookshelf.entity.Book;

public interface BookRepository extends PagingAndSortingRepository<Book, Long>{

}

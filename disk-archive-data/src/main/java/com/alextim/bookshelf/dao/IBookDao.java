package com.alextim.bookshelf.dao;

import java.util.Collection;
import java.util.List;

import com.alextim.bookshelf.entity.Book;

public interface IBookDao {
    Book addBook(Book book);
    Book addBook();

    List<Book> findBook(Integer yearOfPublication, Integer volume);

    Collection<Book> findAll();
    Book getById(Long id);
    void delete(Book book);
}

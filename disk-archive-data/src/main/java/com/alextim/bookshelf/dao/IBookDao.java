package com.alextim.bookshelf.dao;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;

public interface IBookDao {
    Book addBook(Book book);
    Book addBook();

    List<Book> findByAuthors(Set<BookAuthor> bookAuthors);
    List<Book> findAllFromCompleteWork();

    Collection<Book> findAll();
    Book getById(Long id);
    void delete(Book book);
}

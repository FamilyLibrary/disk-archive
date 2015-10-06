package com.alextim.bookshelf.dao;

import java.util.List;
import java.util.Set;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;

public interface IBookDao {
    Book addBook(Book book);
    Book addBook();

    List<Book> findByAuthor(Set<BookAuthor> bookAuthor);
}

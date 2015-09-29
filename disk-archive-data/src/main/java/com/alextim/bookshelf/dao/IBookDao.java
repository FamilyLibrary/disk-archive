package com.alextim.bookshelf.dao;

import com.alextim.bookshelf.entity.Book;

public interface IBookDao {
    Book addBook(Book book);
    Book addBook();
}

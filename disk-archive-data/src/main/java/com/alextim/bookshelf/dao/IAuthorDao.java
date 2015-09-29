package com.alextim.bookshelf.dao;

import com.alextim.bookshelf.entity.BookAuthor;

public interface IAuthorDao {
    BookAuthor addAuthor(BookAuthor author);
    BookAuthor addAuthor();
}

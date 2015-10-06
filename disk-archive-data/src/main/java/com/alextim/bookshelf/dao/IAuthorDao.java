package com.alextim.bookshelf.dao;

import com.alextim.bookshelf.entity.BookAuthor;

public interface IAuthorDao {
    BookAuthor findAuthor(String firstAuthorName, String lastAuthorName);

    BookAuthor addAuthor(BookAuthor author);
    BookAuthor addAuthor();
}

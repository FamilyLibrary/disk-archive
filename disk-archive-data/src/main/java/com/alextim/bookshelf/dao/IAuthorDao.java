package com.alextim.bookshelf.dao;

import com.alextim.bookshelf.entity.Author;

public interface IAuthorDao {
    Author addAuthor(Author author);
    Author addAuthor();
}

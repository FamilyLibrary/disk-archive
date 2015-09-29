package com.alextim.bookshelf.dao;

import com.alextim.bookshelf.entity.BookGroup;

public interface IBookGroupDao {
    BookGroup addGroup(BookGroup bookGroup);
    BookGroup addGroup();
}

package com.alextim.bookshelf.dao.impl;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.general.dao.impl.BasicDAO;

public class BookDaoImpl extends BasicDAO<Book> implements IBookDao {

    @Override
    public Book addBook(final Book book) {
        saveOrUpdate(book);
        return book;
    }

    @Override
    public Book addBook() {
        final Book book = createBookEntity();

        book.setName(Book.NEW_NAME);
        book.setDescription(Book.NEW_DESCRIPTION);

        return addBook(book);
    }

    protected Book createBookEntity() {
        return new Book();
    }

}

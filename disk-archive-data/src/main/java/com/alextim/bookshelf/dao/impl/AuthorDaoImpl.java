package com.alextim.bookshelf.dao.impl;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.entity.Author;
import com.alextim.general.dao.impl.BasicDAO;

public class AuthorDaoImpl extends BasicDAO<Author> implements IAuthorDao {

    @Override
    public Author addAuthor(final Author author) {
        saveOrUpdate(author);
        return author;
    }

    @Override
    public Author addAuthor() {
        final Author author = createAuthorEntity();

        author.setGender(Author.NEW_GENDER);
        author.setFirstName(Author.NEW_FIRST_NAME);
        author.setLastName(Author.NEW_LAST_NAME);

        addAuthor(author);

        return author;
    }

    private Author createAuthorEntity() {
        final Author author = new Author();
        return author;
    }

}

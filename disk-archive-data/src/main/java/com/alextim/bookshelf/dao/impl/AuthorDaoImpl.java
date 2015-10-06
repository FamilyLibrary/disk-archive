package com.alextim.bookshelf.dao.impl;

import org.hibernate.criterion.Restrictions;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.general.dao.impl.BasicDAO;

public class AuthorDaoImpl extends BasicDAO<BookAuthor> implements IAuthorDao {

    private static final String FIRST_NAME_FIELD = "firstName";
    private static final String LAST_NAME_FIELD = "lastName";

    @Override
    public BookAuthor addAuthor(final BookAuthor author) {
        saveOrUpdate(author);
        return author;
    }

    @Override
    public BookAuthor addAuthor() {
        final BookAuthor author = createAuthorEntity();

        author.setGender(BookAuthor.NEW_GENDER);
        author.setFirstName(BookAuthor.NEW_FIRST_NAME);
        author.setLastName(BookAuthor.NEW_LAST_NAME);

        return addAuthor(author);
    }

    @Override
    public BookAuthor findAuthor(final String firstAuthorName, final String lastAuthorName) {
        return (BookAuthor)currentSession().createCriteria(BookAuthor.class)
            .add(Restrictions.like(FIRST_NAME_FIELD, firstAuthorName))
            .add(Restrictions.like(LAST_NAME_FIELD, lastAuthorName))
            .uniqueResult();
    }

    private BookAuthor createAuthorEntity() {
        final BookAuthor author = new BookAuthor();
        return author;
    }
}

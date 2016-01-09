package com.alextim.bookshelf.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.dao.impl.BasicDAO;

@Repository
@Transactional
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

    @Override
    public List<Book> findBook(final Integer yearOfPublication, final Integer volume) {
        final Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(Book.class)
            .add(Restrictions.eq("yearOfPublication", yearOfPublication))
            .add(Restrictions.eqOrIsNull("volume", volume));
        return criteria.list();
    }

    protected Book createBookEntity() {
        return new Book();
    }
}

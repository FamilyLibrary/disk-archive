package com.alextim.bookshelf.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findByAuthors(final Set<BookAuthor> bookAuthors) {
        return getSessionFactory().getCurrentSession().createCriteria(Book.class)
            .add(Restrictions.in("authors", bookAuthors)).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Book> findAllFromCompleteWork() {
        return getSessionFactory().getCurrentSession().createCriteria(Book.class)
                .add(Restrictions.isNotNull("completeWork"))
                .addOrder(Order.asc("completeWork.id")).list();
    }

    protected Book createBookEntity() {
        return new Book();
    }
}

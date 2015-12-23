package com.alextim.bookshelf.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.BookGroup;

@RunWith(MockitoJUnitRunner.class)
public class BookDaoImplTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME_VALUE = "name";
    private static final String DESCRIPTION_VALUE = "description";
    private static final Integer YEAR_OF_PUBLICATION_VALUE = 2015;
    private static final int BOOK_AUTHOR_SIZE = 1;

    @Mock
    private HibernateTemplate hibernateTemplate;

    @Mock
    private Book book;
    @Mock
    private BookGroup bookGroup;
    @Mock
    private BookAuthor bookAuthor;

    @InjectMocks
    private BookDaoImpl dao = new BookDaoImpl();

    @Before
    public void setUp() {
        when(book.getId()).thenReturn(ID_VALUE);
        when(book.getName()).thenReturn(NAME_VALUE);
        when(book.getDescription()).thenReturn(DESCRIPTION_VALUE);
        when(book.getYearOfPublication()).thenReturn(YEAR_OF_PUBLICATION_VALUE);
        when(book.getBookGroup()).thenReturn(bookGroup);
        when(book.getAuthors()).thenReturn(
                new HashSet<BookAuthor>(Arrays.asList(bookAuthor))
        );
    }

    @Test
    public void shouldAddBookWithDefaultValues() {
        final Book resultBook = dao.addBook();

        verify(hibernateTemplate).saveOrUpdate(resultBook);

        assertEquals(Book.NEW_NAME, resultBook.getName());
        assertEquals(Book.NEW_DESCRIPTION, resultBook.getDescription());
        assertNull(resultBook.getYearOfPublication());
        assertNull(resultBook.getBookGroup());
        assertNull(resultBook.getAuthors());
    }

    @Test
    public void shouldAddBookGroupWithPredefinedValues() {
        final Book resultBook = dao.addBook(book);

        verify(hibernateTemplate).saveOrUpdate(book);

        assertEquals(ID_VALUE, resultBook.getId());
        assertEquals(NAME_VALUE, resultBook.getName());
        assertEquals(DESCRIPTION_VALUE, resultBook.getDescription());
        assertEquals(YEAR_OF_PUBLICATION_VALUE, resultBook.getYearOfPublication());
        assertNotNull(resultBook.getAuthors());
        assertEquals(BOOK_AUTHOR_SIZE, resultBook.getAuthors().size());
    }

    @Test(expected=HibernateException.class)
    public void shouldThrowsHibernateExceptionIfValueIsNull() {
        doThrow(new HibernateException("Test Exception"))
            .when(hibernateTemplate).saveOrUpdate(null);
        dao.addBook(null);
    }
}

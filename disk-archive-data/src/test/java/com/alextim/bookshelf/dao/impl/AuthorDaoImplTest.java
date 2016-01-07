package com.alextim.bookshelf.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alextim.Gender;
import com.alextim.bookshelf.entity.BookAuthor;

@RunWith(MockitoJUnitRunner.class)
public class AuthorDaoImplTest {
    private static final Long AUTHOR_ID = 1L;
    private static final String AUTHOR_FIRST_NAME = "firstName";
    private static final String AUTHOR_LAST_NAME = "lastName";
    private static final Gender AUTHOR_GENDER = Gender.MALE;
    private static final Calendar AUTHOR_BIRTHDAY_DATE = 
            GregorianCalendar.from(ZonedDateTime.of(LocalDateTime.of(2015, 9, 29, 0, 0, 0), ZoneId.of("UTC")));

    @Mock
    private HibernateTemplate hibernateTemplate;
    @Mock
    private BookAuthor author;

    @InjectMocks
    private AuthorDaoImpl dao = new AuthorDaoImpl();

    @Before
    public void setUp() {
        when(author.getId()).thenReturn(AUTHOR_ID);
        when(author.getFirstName()).thenReturn(AUTHOR_FIRST_NAME);
        when(author.getLastName()).thenReturn(AUTHOR_LAST_NAME);
        when(author.getGender()).thenReturn(AUTHOR_GENDER);
        when(author.getBirthdayDate()).thenReturn(AUTHOR_BIRTHDAY_DATE);
    }
    
    @Test
    public void shouldAddAuthorWithDefaultValues() {
        final BookAuthor resultAuthor = dao.addAuthor();

        verify(hibernateTemplate).saveOrUpdate(resultAuthor);

        assertEquals(resultAuthor.getFirstName(), BookAuthor.NEW_FIRST_NAME);
        assertEquals(resultAuthor.getLastName(), BookAuthor.NEW_LAST_NAME);
        assertEquals(resultAuthor.getGender(), BookAuthor.NEW_GENDER);
        assertNull(resultAuthor.getBirthdayDate());
    }

    @Test
    public void shouldAddAuthorWithPredefinedValues() {
        final BookAuthor resultAuthor = dao.addAuthor(author);

        verify(hibernateTemplate).saveOrUpdate(author);

        assertEquals(resultAuthor.getId(), AUTHOR_ID);
        assertEquals(resultAuthor.getFirstName(), AUTHOR_FIRST_NAME);
        assertEquals(resultAuthor.getLastName(), AUTHOR_LAST_NAME);
        assertEquals(resultAuthor.getGender(), AUTHOR_GENDER);
        assertEquals(resultAuthor.getBirthdayDate(), AUTHOR_BIRTHDAY_DATE);
    }

    @Test(expected=HibernateException.class)
    public void shouldThrowsHibernateExceptionIfValueIsNull() {
        doThrow(new HibernateException("Test Exception"))
            .when(hibernateTemplate).saveOrUpdate(null);
        dao.addAuthor(null);
    }
}

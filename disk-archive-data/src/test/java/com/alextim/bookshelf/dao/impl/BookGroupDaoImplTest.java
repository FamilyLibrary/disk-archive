package com.alextim.bookshelf.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate5.HibernateTemplate;

import com.alextim.bookshelf.entity.BookGroup;

@RunWith(MockitoJUnitRunner.class)
public class BookGroupDaoImplTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME_VALUE = "GroupNameValue";
    private static final String DESCRIPTION_VALUE = "GroupDescriptionValue";

    @Mock
    private EntityManager entityManager;
    @Mock
    private Session session;
    @Mock
    private BookGroup bookGroup;

    @InjectMocks
    private BookGroupDaoImpl dao = new BookGroupDaoImpl();

    @Before
    public void setUp() {
        when(bookGroup.getId()).thenReturn(ID_VALUE);
        when(bookGroup.getName()).thenReturn(NAME_VALUE);
        when(bookGroup.getDescription()).thenReturn(DESCRIPTION_VALUE);

        when(entityManager.unwrap(Session.class)).thenReturn(session);
    }

    @Test
    public void shouldAddBookGroupWithDefaultValues() {
        final BookGroup resultGroup = dao.addGroup();

        verify(session).saveOrUpdate(resultGroup);

        //TODO Uncomment below line and test id value that will be set by override BookGroupDaoImpl->createGroupEntity method 
        //assertEquals(ID_VALUE, resultGroup.getId());
        assertEquals(BookGroup.NEW_NAME, resultGroup.getName());
        assertEquals(BookGroup.NEW_DESCRIPTION, resultGroup.getDescription());
    }

    @Test
    public void shouldAddBookGroupWithPredefinedValues() {
        final BookGroup resultGroup = dao.addGroup(bookGroup);

        verify(session).saveOrUpdate(bookGroup);

        assertEquals(ID_VALUE, resultGroup.getId());
        assertEquals(NAME_VALUE, resultGroup.getName());
        assertEquals(DESCRIPTION_VALUE, resultGroup.getDescription());
    }

    @Test(expected=HibernateException.class)
    public void shouldThrowsHibernateExceptionIfValueIsNull() {
        doThrow(new HibernateException("Test Exception"))
            .when(session).saveOrUpdate(null);
        dao.addGroup(null);
    }
}

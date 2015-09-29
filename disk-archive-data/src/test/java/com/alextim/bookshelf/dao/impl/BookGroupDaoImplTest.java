package com.alextim.bookshelf.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.hibernate.HibernateException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.alextim.bookshelf.dao.IBookGroupDao;
import com.alextim.bookshelf.entity.BookGroup;

@RunWith(MockitoJUnitRunner.class)
public class BookGroupDaoImplTest {
    private static final Long ID_VALUE = 1L;
    private static final String NAME_VALUE = "GroupNameValue";
    private static final String DESCRIPTION_VALUE = "GroupDescriptionValue";

    @Mock
    private HibernateTemplate hibernateTemplate;
    @Mock
    private BookGroup bookGroup;

    @InjectMocks
    private IBookGroupDao dao = new BookGroupDaoImpl();

    @Before
    public void setUp() {
        when(bookGroup.getId()).thenReturn(ID_VALUE);
        when(bookGroup.getName()).thenReturn(NAME_VALUE);
        when(bookGroup.getDescription()).thenReturn(DESCRIPTION_VALUE);
    }

    @Test
    public void testAddBookGroupWithDefaultValues() {
        final BookGroup resultGroup = dao.addGroup();

        verify(hibernateTemplate).saveOrUpdate(resultGroup);

        //TODO Uncomment below line and test id value that will be set by override method BookGroupDaoImpl->createGroupEntity
        //assertEquals(ID_VALUE, resultGroup.getId());
        assertEquals(BookGroup.NEW_NAME, resultGroup.getName());
        assertEquals(BookGroup.NEW_DESCRIPTION, resultGroup.getDescription());
    }

    @Test
    public void testAddBookGroupWithPredefinedValues() {
        final BookGroup resultGroup = dao.addGroup(bookGroup);

        verify(hibernateTemplate).saveOrUpdate(bookGroup);

        assertEquals(ID_VALUE, resultGroup.getId());
        assertEquals(NAME_VALUE, resultGroup.getName());
        assertEquals(DESCRIPTION_VALUE, resultGroup.getDescription());
    }

    @Test(expected=HibernateException.class)
    public void testThrowsHibernateExceptionIfValueIsNull() {
        doThrow(new HibernateException("Test Exception")).
            when(hibernateTemplate).saveOrUpdate(null);
        dao.addGroup(null);
    }
}

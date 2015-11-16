package com.alextim.bookshelf.service.impl;

import java.io.File;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;
import com.alextim.diskarchive.configuration.ApplicationConfiguration;
import com.alextim.diskarchive.configuration.DataFactoryConfiguration;
import com.alextim.diskarchive.configuration.TestDaoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, DataFactoryConfiguration.class, TestDaoConfiguration.class}, 
                      inheritLocations = true)
public class BookServiceImplTest_CRUDOperations {
    public static final Logger LOG = Logger.getLogger(BookServiceImplTest_CRUDOperations.class);

    @Resource
    private IBookService bookService;

    private Collection<Book> books;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        books = bookService.uploadBookFile();
    }

    @Test
    public void shouldInsertBooks() {
        bookService.insert(books);
    }
}

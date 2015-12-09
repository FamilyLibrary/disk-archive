package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.datauploader.uploader.impl.XlsFileUploaderStrategy;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/data-factory-context.xml"}, inheritLocations = true)
public class XlsUploaderTest {
    public static final Logger LOG = Logger.getLogger(XlsUploaderTest.class);

    @Resource
    private File xlsFile;

    @Mock
    private IBookDao bookDao;

    @Spy
    private UploaderContext uploaderContext;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    private Collection<Book> books;

    @Before
    public void setUp() {
        uploaderContext = Mockito.spy(new UploaderContext(new XlsFileUploaderStrategy(xlsFile)));
        MockitoAnnotations.initMocks(this);

        books = bookService.uploadBookFile();
    }

    @Test
    public void shouldReturnCorrectBookSize() {
        assertEquals(178, books.size());
    }

}

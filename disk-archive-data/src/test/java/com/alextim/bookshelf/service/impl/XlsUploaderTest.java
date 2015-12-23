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
import com.alextim.bookshelf.datauploader.validator.impl.XlsBookValidator;
import com.alextim.bookshelf.entity.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/data-factory-context.xml"}, inheritLocations = true)
public class XlsUploaderTest {
    public static final Logger LOG = Logger.getLogger(XlsUploaderTest.class);

    @Resource
    private File xlsFile;

    @Spy
    private XlsBookValidator validator;
    @Spy
    private UploaderContext uploaderContext;
    @Spy
    @InjectMocks
    private XlsFileUploaderStrategy uploaderStrategy;

    @Mock
    private IBookDao bookDao;
    @Spy
    @InjectMocks
    private BookServiceImpl bookService;

    private Collection<Book> books;

    @Before
    public void setUp() {
        uploaderStrategy = Mockito.spy(new XlsFileUploaderStrategy(xlsFile));
        uploaderContext = Mockito.spy(new UploaderContext(uploaderStrategy));

        MockitoAnnotations.initMocks(this);

        books = bookService.uploadBookFile();
    }

    @Test
    public void shouldReturnCorrectBookSize() {
        assertEquals(177, books.size());
    }

}

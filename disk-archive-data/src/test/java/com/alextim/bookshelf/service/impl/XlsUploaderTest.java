package com.alextim.bookshelf.service.impl;

import java.io.File;

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
import com.alextim.bookshelf.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/data-factory-context.xml"}, inheritLocations = true)
public class XlsUploaderTest {
    public static final Logger LOG = Logger.getLogger(CsvUploaderTest.class);

    @Resource
    private File xlsFile;

    @Mock
    private IBookDao bookDao;

    @Spy
    private UploaderContext uploaderContext;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    @Before
    public void setUp() {
        uploaderContext = Mockito.spy(new UploaderContext(new XlsFileUploaderStrategy(xlsFile)));
        MockitoAnnotations.initMocks(this);
    }
    
    @Test(expected=UnsupportedOperationException.class)
    public void shouldUploadCsvFileAndReturnAllAbsentBooks() {
        bookService.uploadBookFile();
    }

}

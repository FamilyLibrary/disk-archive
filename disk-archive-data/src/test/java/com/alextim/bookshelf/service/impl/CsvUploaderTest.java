package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

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
import com.alextim.bookshelf.datauploader.uploader.impl.CsvFileUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/data-factory-context.xml"}, inheritLocations = true)
public class CsvUploaderTest {
    public static final Logger LOG = Logger.getLogger(CsvUploaderTest.class);

    private static final Object EMPTY_ARRAY = new Integer[]{};

    @Resource
    private File csvFile;

    @Mock
    private IBookDao bookDao;

    @Spy
    private UploaderContext uploaderContext;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    @Before
    public void setUp() {
        uploaderContext = Mockito.spy(new UploaderContext(new CsvFileUploaderStrategy(csvFile)));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldUploadCsvFileAndReturnAllAbsentBooks() {
        Collection<Book> books = bookService.uploadBookFile();
        assertEquals(178, books.size());

        when(bookDao.findAllFromCompleteWork()).thenReturn(new ArrayList<Book>(books));

        List<AbsentVolumesResult> result =  bookService.getAllAbsentBooks(
            new Function<Book, Object>() {
                @Override
                public String apply(Book book) {
                    final String authors = book.getAuthors()
                        .stream()
                        .map(author -> author.getLastName())
                        .collect(Collectors.joining(","));
                    return String.format("%s_%s", authors, book.getYearOfPublication());
                }
            }
        );

        result.stream().forEach(System.out::println);
    }
}

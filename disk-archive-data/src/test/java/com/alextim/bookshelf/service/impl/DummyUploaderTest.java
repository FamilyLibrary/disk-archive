package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.datauploader.validator.impl.CsvBookValidator;
import com.alextim.bookshelf.entity.Book;

@RunWith(MockitoJUnitRunner.class)
public class DummyUploaderTest {
    private static final String LESKOV_LAST_NAME = "Н.С. Лесков";
    private static final String GONCHAROV_LAST_NAME = "И.А. Гончаров";
    private static final String BLOCK_BELIY_LAST_NAME = "Александр Блок,Андрей Белый";
    private static final String SERVANTES_LAST_NAME = "Мигель де Сервантес Сааведра";
    private static final String VOINICH_LAST_NAME = "Этель Лилиан Войнич";

    private static final List<Integer> EMPTY_LIST = Collections.emptyList();

    @Spy
    private CsvBookValidator validator;
    @Spy
    @InjectMocks
    private DummyUploaderStrategy uploaderStrategy;
    @Spy
    @InjectMocks
    private UploaderContext uploaderContext;

    @Mock
    private IBookDao bookDao;

    @Spy
    @InjectMocks
    private BookServiceImpl bookService;

    private Collection<Book> books;

    @Before
    public void setUp() {
        uploaderStrategy = Mockito.spy(new DummyUploaderStrategy());
        uploaderContext = Mockito.spy(new UploaderContext(uploaderStrategy));

        MockitoAnnotations.initMocks(this);

        books = bookService.uploadBookFile();
    }
    
    @Test
    public void shouldReturnCorrectBookSize() {
        assertEquals(12, books.size());
    }

    @Test
    public void shouldReturnAllAbsentBooks() {
        final Map<Object, List<Integer>> result =  bookService.getAllAbsentBooks(books,
            new Function<Book, Object>() {
                @Override
                public String apply(final Book book) {
                    return book.getAuthors()
                            .stream()
                            .map(author -> author.getLastName())
                            .collect(Collectors.joining(","));
                }
            }
        );

        assertEquals(5, result.size());
        assertEquals(EMPTY_LIST, result.get(SERVANTES_LAST_NAME));
        assertEquals(Arrays.asList(new Integer[]{4, 5}), result.get(LESKOV_LAST_NAME));
        assertEquals(EMPTY_LIST, result.get(VOINICH_LAST_NAME));
        assertEquals(Arrays.asList(new Integer[]{4}), result.get(GONCHAROV_LAST_NAME));
        assertEquals(EMPTY_LIST, result.get(BLOCK_BELIY_LAST_NAME));
    }
}

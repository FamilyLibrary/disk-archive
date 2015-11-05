package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploaderStrategy;
import com.alextim.bookshelf.datauploader.uploader.impl.UploaderContext;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.IBookService;

@RunWith(MockitoJUnitRunner.class)
public class DummyUploaderTest {
    private static final String LESKOV_LAST_NAME = "Н.С. Лесков";
    private static final String GONCHAROV_LAST_NAME = "И.А. Гончаров";
    private static final String BLOCK_BELIY_LAST_NAME = "Александр Блок,Андрей Белый";
    private static final String SERVANTES_LAST_NAME = "Мигель де Сервантес Сааведра";
    private static final String VOINICH_LAST_NAME = "Этель Лилиан Войнич";

    private static final List<Integer> EMPTY_LIST = Arrays.asList(new Integer[]{});

    @Spy
    private UploaderContext uploaderContext = new UploaderContext(new DummyUploaderStrategy());

    @Mock
    private IBookDao bookDao;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    private Collection<Book> books;

    @Before
    public void setUp() {
        books = bookService.uploadBookFile();
    }
    
    @Test
    public void shouldReturnCorrectBookSize() {
        assertEquals(12, books.size());
    }

    @Test
    public void shouldReturnAllAbsentBooks() {
        when(bookDao.findAllFromCompleteWork()).thenReturn(new ArrayList<Book>(books));

        Map<Object, List<Integer>> result =  bookService.getAllAbsentBooks(
            new Function<Book, Object>() {
                @Override
                public String apply(Book book) {
                    return book.getAuthors()
                            .stream()
                            .map(author -> author.getLastName())
                            .collect(Collectors.joining(","));
                }
            }
        );

        assertEquals(EMPTY_LIST, result.get(SERVANTES_LAST_NAME));
        assertEquals(Arrays.asList(new Integer[]{4, 5}), result.get(LESKOV_LAST_NAME));
        assertEquals(EMPTY_LIST, result.get(VOINICH_LAST_NAME));
        assertEquals(Arrays.asList(new Integer[]{4}), result.get(GONCHAROV_LAST_NAME));
        assertEquals(EMPTY_LIST, result.get(BLOCK_BELIY_LAST_NAME));
    }
}

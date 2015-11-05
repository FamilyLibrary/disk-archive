package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
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
import com.alextim.bookshelf.service.IBookService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/data-factory-context.xml"}, inheritLocations = true)
public class CsvUploaderTest {
    public static final Logger LOG = Logger.getLogger(CsvUploaderTest.class);

    private static final String LESKOV_KEY = "Н.С. Лесков_1981";
    private static final String GONCHAROV_KEY = "И.А. Гончаров_1981";
    private static final String BLOCK_BELIY_KEY = "Александр Блок,Андрей Белый_1990";
    private static final String SERVANTES_KEY = "Мигель де Сервантес Сааведра_1979";
    private static final String VOINICH_KEY = "Этель Лилиан Войнич_1975";
    private static final String VISHNYA_KEY = "Остап Вишня_1988";
    private static final String NEKRASOV_KEY = "Н.А. Некрасов_1979";
    private static final String TUTCHEV_KEY = "Ф.И. Тютчев_1980";
    private static final String BAYRON_KEY = "Джорж Гордон Байрон_1981";
    private static final String SIRAFIMOVICH_KEY = "А.С. Сирафимович_1980";
    private static final String SOLJENICEN_KEY = "Александр Солженицын_1978";
    private static final String STANUKOVICH_KEY = "К.М. Станюкович_1977";
    private static final String TOLSTOY_KEY = "А.К. Толстой_1980";
    private static final String FADEEV_1982_KEY = "А.А. Фадеев_1982";
    private static final String FADEEV_1979_KEY = "А.А. Фадеев_1979";
    private static final String STIVENSON_KEY = "Р.Л. Стивенсон_1981";
    private static final String GRIN_KEY = "А.С. Грин_1980";
    private static final String TWEN_KEY = "Марк Твен_1980";
    private static final String TEKKEREY_KEY = "Уильям Теккерей_1975";
    private static final String STENDAL_KEY = "Стендаль_1978";
    private static final String LONDON_KEY = "Джек Лондон_1976";
    private static final String GORKIY_KEY = "М. Горький_1979";
    private static final String PUSHKIN_KEY = "Александр Пушкин_1974";
    private static final String MAYAKOVSKIY_KEY = "Владимир Маяковский_1978";

    private static final List<Integer> EMPTY_LIST = Arrays.asList(new Integer[]{});

    @Resource
    private File csvFile;

    @Mock
    private IBookDao bookDao;

    @Spy
    private UploaderContext uploaderContext;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    private Collection<Book> books;

    @Before
    public void setUp() {
        uploaderContext = Mockito.spy(new UploaderContext(new CsvFileUploaderStrategy(csvFile)));
        MockitoAnnotations.initMocks(this);

        books = bookService.uploadBookFile();
    }

    @Test
    public void shouldReturnCorrectBookSize() {
        assertEquals(178, books.size());
    }

    @Test
    public void shouldReturnAllAbsentBooks() {
        when(bookDao.findAllFromCompleteWork()).thenReturn(new ArrayList<Book>(books));

        Map<Object, List<Integer>> result =  bookService.getAllAbsentBooks(
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

        result.entrySet().stream()
            .filter(e -> e.getValue().size() > 0)
            .forEach(System.out::println);

        assertEquals(SERVANTES_KEY, EMPTY_LIST, result.get(SERVANTES_KEY));
        assertEquals(LESKOV_KEY, Arrays.asList(new Integer[]{4, 5}), result.get(LESKOV_KEY));
        assertEquals(VOINICH_KEY, EMPTY_LIST, result.get(VOINICH_KEY));
        assertEquals(GONCHAROV_KEY, Arrays.asList(new Integer[]{4}), result.get(GONCHAROV_KEY));
        assertEquals(VISHNYA_KEY, EMPTY_LIST, result.get(VISHNYA_KEY));
        assertEquals(NEKRASOV_KEY, EMPTY_LIST, result.get(NEKRASOV_KEY));
        assertEquals(TUTCHEV_KEY, Arrays.asList(new Integer[]{3, 4}), result.get(TUTCHEV_KEY));
        assertEquals(BAYRON_KEY, EMPTY_LIST, result.get(BAYRON_KEY));
        assertEquals(SIRAFIMOVICH_KEY, EMPTY_LIST, result.get(SIRAFIMOVICH_KEY));
        //assertEquals(Arrays.asList(EMPTY_ARRAY), result.get(SOLJENICEN_LAST_NAME));
        assertEquals(BLOCK_BELIY_KEY, EMPTY_LIST, result.get(BLOCK_BELIY_KEY));
        assertEquals(STANUKOVICH_KEY, Arrays.asList(new Integer[]{7}), result.get(STANUKOVICH_KEY));
        assertEquals(TOLSTOY_KEY, EMPTY_LIST, result.get(TOLSTOY_KEY));
        assertEquals(FADEEV_1982_KEY, EMPTY_LIST, result.get(FADEEV_1982_KEY));
        assertEquals(FADEEV_1979_KEY, EMPTY_LIST, result.get(FADEEV_1979_KEY));
        assertEquals(STIVENSON_KEY, Arrays.asList(new Integer[]{1, 2}), result.get(STIVENSON_KEY));
        assertEquals(GRIN_KEY, Arrays.asList(new Integer[]{2}), result.get(GRIN_KEY));
        assertEquals(TWEN_KEY, EMPTY_LIST, result.get(TWEN_KEY));
        assertEquals(TEKKEREY_KEY, Arrays.asList(new Integer[]{4, 5, 10, 11}), result.get(TEKKEREY_KEY));
        assertEquals(STENDAL_KEY, EMPTY_LIST, result.get(STENDAL_KEY));
        assertEquals(LONDON_KEY, EMPTY_LIST, result.get(LONDON_KEY));
        assertEquals(GORKIY_KEY, EMPTY_LIST, result.get(GORKIY_KEY));
        assertEquals(PUSHKIN_KEY, EMPTY_LIST, result.get(PUSHKIN_KEY));
        assertEquals(MAYAKOVSKIY_KEY, Arrays.asList(new Integer[]{5, 6, 7, 9}), result.get(MAYAKOVSKIY_KEY));
        
    }
}

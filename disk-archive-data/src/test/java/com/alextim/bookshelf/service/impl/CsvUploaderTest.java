package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
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

    private static final String BROKGAUS_EFRON_LAST_NAME = "Ф.А. Брокгауз,И.А. Ефрон";
    
    private static final String BROKGAUS_EFRON_1891_KEY = BROKGAUS_EFRON_LAST_NAME + "_1891";
    private static final String BROKGAUS_EFRON_1893_KEY = BROKGAUS_EFRON_LAST_NAME + "_1893";
    private static final String BROKGAUS_EFRON_1898_KEY = BROKGAUS_EFRON_LAST_NAME + "_1898";
    private static final String BROKGAUS_EFRON_1900_KEY = BROKGAUS_EFRON_LAST_NAME + "_1900";
    private static final String BROKGAUS_EFRON_1901_KEY = BROKGAUS_EFRON_LAST_NAME + "_1901";
    private static final String BROKGAUS_EFRON_1902_KEY = BROKGAUS_EFRON_LAST_NAME + "_1902";
    private static final String BROKGAUS_EFRON_1903_KEY = BROKGAUS_EFRON_LAST_NAME + "_1903";

    private static final List<Integer> EMPTY_LIST = Collections.emptyList();
    private static final Function<Book, Object> AUTHOR_YEAR_PUBLICATION_FUNCTION = 
            new Function<Book, Object>() {
        @Override
        public String apply(final Book book) {
            final String authorNames = book.getAuthors()
                .stream()
                .map(author -> author.getLastName())
                .collect(Collectors.joining(","));
            return String.format("%s_%s", authorNames, book.getYearOfPublication());
        }
    };

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
        final Map<Object, List<Integer>> result =  bookService.getAllAbsentBooks(books, AUTHOR_YEAR_PUBLICATION_FUNCTION);
        result.entrySet().stream()
            .filter(e -> e.getValue().size() > 0)
            .forEach(System.out::println);

        assertEquals(46, result.size());
        assertEquals(SERVANTES_KEY, EMPTY_LIST, result.get(SERVANTES_KEY));
        assertEquals(LESKOV_KEY, Arrays.asList(new Integer[]{4, 5}), result.get(LESKOV_KEY));
        assertEquals(VOINICH_KEY, EMPTY_LIST, result.get(VOINICH_KEY));
        assertEquals(GONCHAROV_KEY, Arrays.asList(new Integer[]{4}), result.get(GONCHAROV_KEY));
        assertEquals(VISHNYA_KEY, EMPTY_LIST, result.get(VISHNYA_KEY));
        assertEquals(NEKRASOV_KEY, EMPTY_LIST, result.get(NEKRASOV_KEY));
        assertEquals(TUTCHEV_KEY, Arrays.asList(new Integer[]{3, 4}), result.get(TUTCHEV_KEY));
        assertEquals(BAYRON_KEY, EMPTY_LIST, result.get(BAYRON_KEY));
        assertEquals(SIRAFIMOVICH_KEY, EMPTY_LIST, result.get(SIRAFIMOVICH_KEY));
        assertEquals(SOLJENICEN_KEY, Arrays.asList(new Integer[]{1}), result.get(SOLJENICEN_KEY));
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

    @Test
    public void shouldReturnAbsentBooksForMultiYearPublication() {
        final Set<BookAuthor> authors  = new HashSet<>();
        authors.add(createBrockgusEfronBookAuthor());

        final Map<Object, List<Integer>> result =  bookService.getAllAbsentBooks(books, authors, AUTHOR_YEAR_PUBLICATION_FUNCTION);

        assertEquals(7, result.size());
        assertEquals(BROKGAUS_EFRON_1891_KEY, Arrays.asList(new Integer[]{4, 5, 6, 8, 9}), result.get(BROKGAUS_EFRON_1891_KEY));
        assertEquals(BROKGAUS_EFRON_1893_KEY, Arrays.asList(new Integer[]{16, 17, 18, 19, 20}), result.get(BROKGAUS_EFRON_1893_KEY));
        assertEquals(BROKGAUS_EFRON_1898_KEY, Arrays.asList(new Integer[]{46, 47, 48, 49, 50}), result.get(BROKGAUS_EFRON_1898_KEY));
        assertEquals(BROKGAUS_EFRON_1900_KEY, Arrays.asList(new Integer[]{56, 58, 59, 60, 61}), result.get(BROKGAUS_EFRON_1900_KEY));
        assertEquals(BROKGAUS_EFRON_1901_KEY, Arrays.asList(new Integer[]{62, 63, 64, 65, 66}), result.get(BROKGAUS_EFRON_1901_KEY));
        assertEquals(BROKGAUS_EFRON_1902_KEY, Arrays.asList(new Integer[]{68, 69, 70, 72}), result.get(BROKGAUS_EFRON_1902_KEY));
        assertEquals(BROKGAUS_EFRON_1903_KEY, Arrays.asList(new Integer[]{73, 74, 75, 77, 78}), result.get(BROKGAUS_EFRON_1903_KEY));
    }

    private BookAuthor createBrockgusEfronBookAuthor() {
        final BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setLastName(BROKGAUS_EFRON_LAST_NAME);
        return bookAuthor;
    }
}

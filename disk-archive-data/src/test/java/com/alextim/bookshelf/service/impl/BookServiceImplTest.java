package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.IBookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    private static final int TOTAL_ABSENT_SHOLOHOV_BOOKS = 10;
    private static final List<Integer> ABSENT_BOOK_AUTHOR1 = Arrays.asList(3, 5, 6, 7, 8, 9, 10, 11, 12);
    private static final List<Integer> ABSENT_BOOK_AUTHOR2 = Arrays.asList(3, 4);
    private static final List<Integer> ABSENT_SHOLOHOV_BOOKS = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    private static final Function<Book, Object> AUTHOR_FUNCTION = new Function<Book, Object>() {
        @Override
        public Long apply(Book book) {
            final BookAuthor author = book.getAuthors().iterator().next();
            return author.getId();
        };
    };

    @Mock
    private IBookDao bookDao;
    @Mock
    private IAuthorDao authorDao;

    @Mock
    private Book book;

    @Mock
    private Book author1bookVol1; //1
    @Mock
    private Book author1bookVol2; //2
    @Mock
    private Book author1bookVol4; //4

    @Mock
    private Book author2bookVol1; //1
    @Mock
    private Book author2bookVol2; //2

    @Mock
    private BookAuthor author1;
    @Mock
    private BookAuthor author2;

    @Mock
    private CompleteWork author1completeWork;
    @Mock
    private CompleteWork author2completeWork;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    private Set<BookAuthor> author1Set;
    private Set<BookAuthor> author2Set;

    @Before
    public void setUp() {
        List<Book> author1Books = Arrays.asList(new Book[]{author1bookVol1, author1bookVol2, author1bookVol4});
        when(author1.getId()).thenReturn(1L);
        
        when(author1bookVol1.getVolume()).thenReturn(1);
        when(author1bookVol2.getVolume()).thenReturn(2);
        when(author1bookVol4.getVolume()).thenReturn(4);

        mockCompleteWorkForBooks(author1Books, author1completeWork, 12);
        mockAuthorsForBooks(author1Books, author1Set, author1);

        List<Book> author2Books = Arrays.asList(new Book[]{author2bookVol1, author2bookVol2});
        when(author2.getId()).thenReturn(2L);

        when(author2bookVol1.getVolume()).thenReturn(1);
        when(author2bookVol2.getVolume()).thenReturn(2);
        
        mockCompleteWorkForBooks(author2Books, author2completeWork, 4);
        mockAuthorsForBooks(author2Books, author2Set, author2);
    }

    @Test
    public void shouldReturnAllAbsentBooks() {
        when(bookDao.findAllFromCompleteWork()).thenReturn(Arrays.asList(
                author1bookVol1, author1bookVol2, author1bookVol4, author2bookVol1, author2bookVol2));

        Map<Object, List<Integer>> result = bookService.getAllAbsentBooks(AUTHOR_FUNCTION);

        assertEquals(2, result.size());
        assertTrue(result.containsKey(author1.getId()));
        assertEquals(ABSENT_BOOK_AUTHOR1, result.get(author1.getId()));
        assertTrue(result.containsKey(author2.getId()));
        assertEquals(ABSENT_BOOK_AUTHOR2, result.get(author2.getId()));
    }

    @Test
    public void shouldReturnAbsentBooksByAuthor() {
        when(bookDao.findByAuthors(author1Set)).thenReturn(Arrays.asList(author1bookVol1, author1bookVol2));
        Map<Object, List<Integer>> result = bookService.getAllAbsentBooks(author1Set, AUTHOR_FUNCTION);

        assertEquals(1, result.size());
        assertEquals(ABSENT_SHOLOHOV_BOOKS, result.get(author1.getId()));
        assertEquals(TOTAL_ABSENT_SHOLOHOV_BOOKS, result.get(author1.getId()).size());
    }

    private void mockAuthorsForBooks(final List<Book> books, final Set<BookAuthor> authors, final BookAuthor author) {
        books.stream().forEach(bookAuthor -> when(bookAuthor.getAuthors()).thenReturn(
                Stream.of(author).collect(Collectors.toSet())
        ));
    }

    private void mockCompleteWorkForBooks(final List<Book> books, final CompleteWork completeWork, final Integer totalVolumes) {
        books.stream().forEach(bookAuthor -> when(bookAuthor.getCompleteWork()).thenReturn(completeWork));
        when(completeWork.getTotalVolumes()).thenReturn(totalVolumes);

        when(completeWork.getFirstVolumeInYear()).thenReturn(null);
        when(completeWork.getLastVolumeInYear()).thenReturn(null);
    }
}

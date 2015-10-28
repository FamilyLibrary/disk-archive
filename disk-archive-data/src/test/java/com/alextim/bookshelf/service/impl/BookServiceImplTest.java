package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.datauploader.uploader.IUploader;
import com.alextim.bookshelf.datauploader.uploader.impl.DummyUploader;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.IBookService;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    private static final String BOOK_AUTHOR_FIRST_NAME = "Михаил";
    private static final String BOOK_AUTHOR_LAST_NAME = "Шолохов";

    private static final int TOTAL_ABSENT_RECORD = 10;
    private static final List<Integer> ABSENT_BOOK_AUTHOR1 = Arrays.asList(3, 5, 6, 7, 8, 9, 10, 11, 12);
    private static final List<Integer> ABSENT_BOOK_AUTHOR2 = Arrays.asList(3, 4);
    private static final List<Integer> ABSENT_SHOLOHOV_BOOKS = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

    @Mock
    private IBookDao bookDao;
    @Mock
    private IAuthorDao authorDao;
    @Spy
    private IUploader dummyInstance = new DummyUploader();

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
        when(author1bookVol1.getVolume()).thenReturn(1);
        when(author1bookVol2.getVolume()).thenReturn(2);
        when(author1bookVol4.getVolume()).thenReturn(4);
        Arrays.asList(author1bookVol1, author1bookVol2, author1bookVol4).stream()
            .forEach(bookAuthor -> when(bookAuthor.getCompleteWork()).thenReturn(author1completeWork));

        when(author2bookVol1.getVolume()).thenReturn(1);
        when(author2bookVol2.getVolume()).thenReturn(2);
        Arrays.asList(author2bookVol1, author2bookVol2).stream()
            .forEach(bookAuthor -> when(bookAuthor.getCompleteWork()).thenReturn(author2completeWork));

        when(author1.getId()).thenReturn(1L);
        author1Set = Arrays.asList(author1).stream().collect(Collectors.toSet());
        Arrays.asList(author1bookVol1, author1bookVol2, author1bookVol4).stream()
            .forEach(bookAuthor -> when(bookAuthor.getAuthors()).thenReturn(author1Set));

        when(author2.getId()).thenReturn(2L);
        author2Set = Arrays.asList(author2).stream().collect(Collectors.toSet());
        Arrays.asList(author2bookVol1, author2bookVol2).stream()
            .forEach(bookAuthor -> when(bookAuthor.getAuthors()).thenReturn(author2Set));

        when(author1completeWork.getTotalVolumes()).thenReturn(12);
        when(author2completeWork.getTotalVolumes()).thenReturn(4);
    }

    @Test
    public void shouldReturnAllAbsentBooks() {
        when(bookDao.findAllFromCompleteWork()).thenReturn(Arrays.asList(
                author1bookVol1, author1bookVol2, author1bookVol4, author2bookVol1, author2bookVol2));

        List<AbsentVolumesResult> result = bookService.getAllAbsentBooks();

        Collections.sort(result, new Comparator<AbsentVolumesResult>() {
            @Override
            public int compare(AbsentVolumesResult result1, AbsentVolumesResult result2) {
                return result1.getBookAuthor().getId().compareTo(result2.getBookAuthor().getId());
            }
        });

        assertEquals(2, result.size());
        assertEquals(author1, result.get(0).getBookAuthor());
        assertEquals(ABSENT_BOOK_AUTHOR1, result.get(0).getAbsentVolumes());
        assertEquals(author2, result.get(1).getBookAuthor());
        assertEquals(ABSENT_BOOK_AUTHOR2, result.get(1).getAbsentVolumes());
    }

    @Test
    public void shouldReturnAbsentBooksByAuthor() {
        when(authorDao.findAuthor(BOOK_AUTHOR_FIRST_NAME, BOOK_AUTHOR_LAST_NAME)).thenReturn(author1);
        when(bookDao.findByAuthor(author1Set)).thenReturn(Arrays.asList(author1bookVol1, author1bookVol2));

        List<Integer> absentBooks = bookService.getAllAbsentBooks(BOOK_AUTHOR_FIRST_NAME, BOOK_AUTHOR_LAST_NAME);

        assertEquals(TOTAL_ABSENT_RECORD, absentBooks.size());
        assertEquals(ABSENT_SHOLOHOV_BOOKS, absentBooks);
    }

    @Test(expected=UnsupportedOperationException.class)
    public void shouldUploadFile() {
        bookService.uploadBookFile();
    }
}

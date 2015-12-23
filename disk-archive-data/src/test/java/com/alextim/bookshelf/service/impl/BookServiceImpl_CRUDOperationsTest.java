package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alextim.bookshelf.dao.IAuthorDao;
import com.alextim.bookshelf.dao.ICompleteWorkDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.IBookService;
import com.alextim.diskarchive.configuration.ApplicationConfiguration;
import com.alextim.diskarchive.configuration.DataFactoryConfiguration;
import com.alextim.diskarchive.configuration.TestDaoConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class, DataFactoryConfiguration.class, TestDaoConfiguration.class}, 
                      inheritLocations = true)
@Rollback(true)
public class BookServiceImpl_CRUDOperationsTest {
    public static final Logger LOG = Logger.getLogger(BookServiceImpl_CRUDOperationsTest.class);

    private static final String TEST_BOOK_NAME = "Test Book Name";
    private static final Integer TEST_BOOK_YEAR_OF_PUBLICATION = 1980;
    private static final Integer TOTAL_VOLUMES = 800;

    @Resource
    private IBookService bookService;

    @Resource
    private ICompleteWorkDao completeWorkDao;

    @Resource
    private IAuthorDao bookAuthorDao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldInsertBooks() {
        final Collection<Book> books = bookService.uploadBookFile();
        final Collection<Book> savedBooks = bookService.save(books);

        assertNotNull(savedBooks);
        assertEquals(177, savedBooks.size());
    }

    @Test
    public void shouldInsertBookWithTheSameCompleteWork() {
        final Book book1 = createTestBook();
        final Book book2 = createTestBook();

        final CompleteWork completeWork = new CompleteWork();
        completeWork.setTotalVolumes(TOTAL_VOLUMES);

        book1.setCompleteWork(completeWork);
        book2.setCompleteWork(completeWork);

        List<Book> savedBooks = bookService.save(Arrays.asList(book1, book2));

        final Book savedBook1 = savedBooks.get(0);
        final Book savedBook2 = savedBooks.get(1);

        assertNotNull(savedBook1);
        assertNotNull(savedBook2);

        assertNotNull(savedBook1.getCompleteWork());
        assertNotNull(savedBook2.getCompleteWork());

        assertEquals(savedBook1.getCompleteWork().getId(), 
                savedBook2.getCompleteWork().getId());
        assertEquals(savedBook1.getCompleteWork().getTotalVolumes(), 
                savedBook2.getCompleteWork().getTotalVolumes());
    }

    @Test
    public void shouldInsertBookWithTheSameAuthors() {
        final Book book1 = createTestBook();
        final Book book2 = createTestBook();
        
        final BookAuthor bookAuthor = new BookAuthor();
        bookAuthor.setLastName("Test Author");

        book1.setAuthors(Stream.of(bookAuthor).collect(Collectors.toSet()));
        book2.setAuthors(Stream.of(bookAuthor).collect(Collectors.toSet()));

        List<Book> savedBooks = bookService.save(Arrays.asList(book1, book2));

        final Book savedBook1 = savedBooks.get(0);
        final Book savedBook2 = savedBooks.get(1);
    
        assertNotNull(savedBook1);
        assertNotNull(savedBook2);

        assertNotNull(savedBook1.getAuthors());
        assertNotNull(savedBook2.getAuthors());

        BookAuthor author1 = savedBook1.getAuthors().iterator().next();
        BookAuthor author2 = savedBook2.getAuthors().iterator().next();

        assertEquals(author1.getId(), author2.getId());
    }

    private Book createTestBook() {
        final Book book = new Book();

        book.setName(TEST_BOOK_NAME);
        book.setYearOfPublication(TEST_BOOK_YEAR_OF_PUBLICATION);

        return book;
    }
}

package com.alextim.bookshelf.service.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

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
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.service.IBookService;

@RunWith(MockitoJUnitRunner.class)
public class DummyUploaderTest {
    @Spy
    private UploaderContext uploaderContext = new UploaderContext(new DummyUploaderStrategy());

    @Mock
    private IBookDao bookDao;

    @InjectMocks
    private IBookService bookService = new BookServiceImpl();

    @Test
    public void shouldUploadDummyFileAndReturnAllAbsentBooks() {
        final Collection<Book> books = bookService.uploadBookFile();
        when(bookDao.findAllFromCompleteWork()).thenReturn(new ArrayList<Book>(books));

        List<AbsentVolumesResult> result =  bookService.getAllAbsentBooks(
            new Function<Book, Object>() {
                @Override
                public String apply(Book book) {
                    final BookAuthor author = book.getAuthors().iterator().next();
                    return author.getLastName();
                }
            }
        );

        assertEquals(11, books.size());

        assertEquals(Arrays.asList(new Integer[]{}), result.get(0).getAbsentVolumes());
        assertEquals(Arrays.asList(new Integer[]{4, 5}), result.get(1).getAbsentVolumes());
        assertEquals(Arrays.asList(new Integer[]{}), result.get(2).getAbsentVolumes());
        assertEquals(Arrays.asList(new Integer[]{4}), result.get(3).getAbsentVolumes());
    }

}

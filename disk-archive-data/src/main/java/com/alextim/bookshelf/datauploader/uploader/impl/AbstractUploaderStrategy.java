package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.entity.TimestampColumns;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";

    private final Map<Book, CompleteWork> completeWorkMap = new ConcurrentHashMap<>();
    private final Map<String, BookAuthor> bookAuthorMap = new ConcurrentHashMap<>();

    protected Book convertToBook(final BookRow bookRow) {
        final Book book = new Book();
        /*TODO: Hibernate won't save a timestampColumns object that contains updated and created fields 
         * if the timestampColumns object is null before calling saveOrUpdate function.
         * Investigate a possibility to create a timestampColumns object during creation of a book object. 
         */ 
        book.setTimestampColumns(new TimestampColumns());

        createGeneralFields(book, bookRow);
        createBookAuthor(book, bookRow);
        if (bookRow.getVolumes() != null) {
            getOrCreateCompleteWork(book, bookRow);
        }
        return book;
    }

    private void createGeneralFields(final Book book, final BookRow bookRow) {
        book.setName(bookRow.getName());
        book.setVolume(bookRow.getVolume());
        book.setYearOfPublication(bookRow.getYearOfPublication());
    }

    private void createBookAuthor(final Book book, final BookRow bookRow) {
        final String[] authorArray = Optional.ofNullable(bookRow.getAuthor())
                .orElse("").split(AUTHOR_SEPARATOR, -1);
        final Set<BookAuthor> authors = Stream.of(authorArray)
                    .filter(authorName -> (authorName != null && !authorName.isEmpty()))
                    .map(authorName -> getOrCreateAuthor(authorName))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        book.setAuthors(authors);
    }

    private BookAuthor getOrCreateAuthor(final String authorName) {
        BookAuthor author = bookAuthorMap.get(authorName);
        if (author == null) {
            author = new BookAuthor();
            author.setLastName(authorName);

            bookAuthorMap.put(authorName, author);
        }
        return author;
    }

    private void getOrCreateCompleteWork(final Book book, final BookRow bookRow) {
        CompleteWork completeWork = completeWorkMap.get(book);
        if (completeWork == null) {
            completeWork = initCompleteWork(book, bookRow);
            completeWorkMap.put(book, completeWork);
        }
        book.setCompleteWork(completeWork);
    }

    private CompleteWork initCompleteWork(final Book book, final BookRow bookRow) {
        final CompleteWork completeWork = new CompleteWork();

        completeWork.setTotalVolumes(Integer.valueOf(bookRow.getVolumes()));

        completeWork.setFirstVolumeInYear(bookRow.getFirstVolumeInYear());
        completeWork.setLastVolumeInYear(bookRow.getLastVolumeInYear());

        return completeWork;
    }
}

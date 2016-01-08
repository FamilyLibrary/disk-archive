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

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";

    private final Map<Book, CompleteWork> completeWorkMap = new ConcurrentHashMap<>();

    protected Book convertToBook(final BookRow bookRow) {
        final Set<BookAuthor> authors = createBookAuthor(bookRow);

        Book book = new Book();

        createGeneralFields(book, bookRow);
        book.setAuthors(authors);
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

    private Set<BookAuthor> createBookAuthor(final BookRow bookRow) {
        final String[] authorArray = Optional.ofNullable(bookRow.getAuthor())
                .orElse("").split(AUTHOR_SEPARATOR, -1);
        final Set<BookAuthor> authors = Stream.of(authorArray)
                    .filter(authorName -> (authorName != null && !authorName.isEmpty()))
                    .map(authorName -> getOrCreateAuthor(authorName))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
        return authors;
    }

    private BookAuthor getOrCreateAuthor(final String authorName) {
        final BookAuthor author = new BookAuthor();
        author.setLastName(authorName);
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

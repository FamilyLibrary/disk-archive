package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";

    private Map<Book, CompleteWork> completeWorkMap = new ConcurrentHashMap<>();
    private Map<String, BookAuthor> bookAuthorMap = new ConcurrentHashMap<>();

    protected Book convertToBook(BookRow bookRow) {
        final Book book = new Book();

        createGeneralFields(book, bookRow);
        createBookAuthor(book, bookRow);
        if (StringUtils.isNotBlank(bookRow.getVolumes())) {
            getOrCreateCompleteWork(book, bookRow);
        }
        return book;
    }

    private void createGeneralFields(final Book book, final BookRow bookRow) {
        book.setName(bookRow.getName());

        updateIntegerField(bookRow::getVolume, book::setVolume);
        updateIntegerField(bookRow::getYearOfPublication, book::setYearOfPublication);
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

    private void getOrCreateCompleteWork(final Book book, final BookRow row) {
        CompleteWork completeWork = completeWorkMap.get(book);
        if (completeWork == null) {
            completeWork = initCompleteWork(book, row);
            completeWorkMap.put(book, completeWork);
        }
        book.setCompleteWork(completeWork);
    }

    private CompleteWork initCompleteWork(final Book book, final BookRow row) {
        final CompleteWork completeWork = new CompleteWork();

        completeWork.setTotalVolumes(Integer.valueOf(row.getVolumes()));

        updateIntegerField(row::getFirstVolumeInYear, completeWork::setFirstVolumeInYear);
        updateIntegerField(row::getLastVolumeInYear, completeWork::setLastVolumeInYear);

        return completeWork;
    }

    private void updateIntegerField(final Supplier<String> supplier, final Consumer<Integer> consumer) {
        final String volumeInYear = supplier.get();
        if (StringUtils.isNotBlank(volumeInYear)) {
            consumer.accept(Integer.valueOf(volumeInYear));
        }
    }
}

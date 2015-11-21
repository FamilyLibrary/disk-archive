package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    private Map<Book, CompleteWork> completeWorkMap = new ConcurrentHashMap<>();
    private Map<String, BookAuthor> bookAuthorMap = new ConcurrentHashMap<>();

    protected Book mapToBook(final String line) {
        final Row row = createRow(Stream
            .of(line.split(REG_EXP_SEPARATOR, -1))
            .collect(Collectors.toList()));

        final Book book = new Book();

        createGeneralFields(book, row);
        createBookAuthor(book, row);
        if (StringUtils.isNotBlank(row.volumes)) {
            getOrCreateCompleteWork(book, row);
        }

        return book;
    }

    private void createGeneralFields(final Book book, final Row row) {
        book.setName(row.name);
        if (StringUtils.isNotBlank(row.volume)) {
            book.setVolume(Integer.valueOf(row.volume));
        }
        if (StringUtils.isNotBlank(row.yearOfPublication)) {
            book.setYearOfPublication(Integer.valueOf(row.yearOfPublication));
        }
    }

    private void createBookAuthor(final Book book, final Row row) {
        final Set<BookAuthor> authors =  Stream.of(row.author.split(AUTHOR_SEPARATOR, -1))
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

    private void getOrCreateCompleteWork(final Book book, final Row row) {
        CompleteWork completeWork = completeWorkMap.get(book);
        if (completeWork == null) {
            completeWork = initCompleteWork(book, row);
            completeWorkMap.put(book, completeWork);
        }
        book.setCompleteWork(completeWork);
    }
    private CompleteWork initCompleteWork(final Book book, final Row row) {
        final CompleteWork completeWork = new CompleteWork();

        completeWork.setTotalVolumes(Integer.valueOf(row.volumes));

        if (StringUtils.isNotBlank(row.firstVolumeInYear)) {
            completeWork.setFirstVolumeInYear(Integer.valueOf(row.firstVolumeInYear));
        }
        if (StringUtils.isNotBlank(row.lastVolumeInYear)) {
            completeWork.setLastVolumeInYear(Integer.valueOf(row.lastVolumeInYear));
        }
        return completeWork;
    }

    private Row createRow(final List<String> parts) {
        final Row row = new Row();

        row.author = parts.get(0);
        row.name = parts.get(1);
        row.volume = parts.get(2);
        row.volumes = parts.get(3);
        row.yearOfPublication = parts.get(4);
        row.firstVolumeInYear = parts.get(5);
        row.lastVolumeInYear = parts.get(6);

        return row;
    }

    private static final class Row {
        private String author;
        private String name;
        private String volume;
        private String volumes;
        private String yearOfPublication;
        private String firstVolumeInYear;
        private String lastVolumeInYear;
    }
}

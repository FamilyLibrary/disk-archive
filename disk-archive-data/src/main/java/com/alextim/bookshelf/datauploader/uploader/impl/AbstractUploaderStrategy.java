package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = ",";
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    protected Book mapToBook(final String line) {
        final Row row = createRow(Stream
            .of(line.split(REG_EXP_SEPARATOR))
            .collect(Collectors.toList()));

        final Book book = new Book();

        createGeneralFields(book, row);
        createBookAuthor(book, row);
        if (StringUtils.isNotBlank(row.volumes)) {
            createCompleteWork(book, row);
        }

        return book;
    }

    private void createGeneralFields(final Book book, final Row row) {
        book.setName(row.name);
        if (StringUtils.isNotBlank(row.volume)) {
            book.setVolume(Integer.valueOf(row.volume));
        }
        if (StringUtils.isNotBlank(row.startYear)) {
            book.setYearOfPublication(Integer.valueOf(row.startYear));
        }
    }
    private void createBookAuthor(Book book, Row row) {
        book.setAuthors(
            Stream.of(row.author.split(AUTHOR_SEPARATOR)).flatMap(authorName -> {
                final BookAuthor author = new BookAuthor();
                author.setLastName(authorName);
                return Stream.of(author);
            }).collect(Collectors.toSet())
        );
    }
    private void createCompleteWork(Book book, Row row) {
        CompleteWork completeWork = new CompleteWork();
        completeWork.setTotalVolumes(Integer.valueOf(row.volumes));
        book.setCompleteWork(completeWork);
    }

    private Row createRow(List<String> parts) {
        final Row row = new Row();

        row.author = getValue(parts, 0);
        row.name = getValue(parts, 1);
        row.volume = getValue(parts, 2);
        row.volumes = getValue(parts, 3);
        row.startYear = getValue(parts, 4);

        return row;
    }

    private String getValue(final List<String> parts, final int index) {
        if (index < parts.size()) {
            return parts.get(index);
        }
        return null;
    }

    private static final class Row {
        private String author;
        private String name;
        private String volume;
        private String volumes;
        private String startYear;
    }
}

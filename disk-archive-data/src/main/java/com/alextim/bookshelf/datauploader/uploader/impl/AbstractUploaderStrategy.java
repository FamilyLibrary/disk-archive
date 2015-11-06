package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    protected Book mapToBook(final String line) {
        final Row row = createRow(Stream
            .of(line.split(REG_EXP_SEPARATOR, -1))
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
        if (StringUtils.isNotBlank(row.yearOfPublication)) {
            book.setYearOfPublication(Integer.valueOf(row.yearOfPublication));
        }
    }
    private void createBookAuthor(final Book book, final Row row) {
        book.setAuthors(
            Stream.of(row.author.split(AUTHOR_SEPARATOR, -1))
            .filter(authorName -> (authorName != null && !authorName.isEmpty()))
            .map(authorName -> {
                final BookAuthor author = new BookAuthor();
                author.setLastName(authorName);
                return author;
            }).collect(Collectors.toCollection(LinkedHashSet::new))
        );
    }
    private void createCompleteWork(Book book, Row row) {
        CompleteWork completeWork = new CompleteWork();

        completeWork.setTotalVolumes(Integer.valueOf(row.volumes));

        if (StringUtils.isNotBlank(row.firstVolumeInYear)) {
            completeWork.setFirstVolumeInYear(Integer.valueOf(row.firstVolumeInYear));
        }
        if (StringUtils.isNotBlank(row.lastVolumeInYear)) {
            completeWork.setLastVolumeInYear(Integer.valueOf(row.lastVolumeInYear));
        }
        book.setCompleteWork(completeWork);
    }

    private Row createRow(List<String> parts) {
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

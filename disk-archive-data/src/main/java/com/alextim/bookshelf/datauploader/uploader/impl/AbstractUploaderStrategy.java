package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    private Map<CompleteWorkKey, CompleteWork> completeWorkMap = new HashMap<>();

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

    private void getOrCreateCompleteWork(final Book book, final Row row) {
        final CompleteWorkKey key = new CompleteWorkKey();
        key.setBook(book);

        CompleteWork completeWork = completeWorkMap.get(key);
        if (completeWork == null) {
            completeWork = initCompleteWork(book, row);
            completeWorkMap.put(key, completeWork);
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

    private static class CompleteWorkKey {
        private static final Function<CompleteWorkKey, Book> CHECK_BOOK_FUNCTION = 
                (completeWork) -> Optional.ofNullable(completeWork.book).orElseThrow(() -> new IllegalStateException("A complete Work must have a book"));
        private static final Function<CompleteWorkKey, Optional<Integer>> YEAR_OF_PUBLICATION_FUNCTION =  
                (completeWork) -> Optional.ofNullable(CHECK_BOOK_FUNCTION.apply(completeWork).getYearOfPublication());
        private static final Function<CompleteWorkKey, Optional<Set<BookAuthor>>> AUTHORS_FUNCTION =  
                 (completeWork) -> Optional.ofNullable(CHECK_BOOK_FUNCTION.apply(completeWork).getAuthors());

         private Book book;

         public void setBook(Book book) {
             this.book = book;
         }

         @Override
         public boolean equals(final Object completeWorkKeyObj) {
             if (!(completeWorkKeyObj instanceof CompleteWorkKey)) {
                 return false;
             }
             if (this == completeWorkKeyObj) {
                 return true;
             }

             final CompleteWorkKey completeWorkKey = (CompleteWorkKey)completeWorkKeyObj;

             return (YEAR_OF_PUBLICATION_FUNCTION.apply(completeWorkKey).equals(YEAR_OF_PUBLICATION_FUNCTION.apply(this)) 
                     && AUTHORS_FUNCTION.apply(completeWorkKey).equals(AUTHORS_FUNCTION.apply(this)));
         }

         @Override
         public int hashCode() {
             int result = 17;

             result = 31 * result + (YEAR_OF_PUBLICATION_FUNCTION.apply(this).orElse(0).hashCode());
             result = 31 * result + (AUTHORS_FUNCTION.apply(this).orElse(Collections.emptySet()).hashCode());

             return result;
         }

    }
}

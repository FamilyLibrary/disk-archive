package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import com.alextim.bookshelf.dao.IBookDao;
import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;
import com.alextim.bookshelf.entity.CompleteWork;

public class AbstractUploaderStrategy {
    private static final String AUTHOR_SEPARATOR = "[\"]\\s*|\\s*,\\s*|\\s*[\"]";

    @Resource
    private IBookDao bookDao;

    protected Book convertToBook(final BookRow bookRow) {
        final Set<BookAuthor> authors = getBookAuthor(bookRow);

        final List<Book> findedBooks =
                bookDao.findBook(bookRow.getYearOfPublication(), bookRow.getVolume());
        Book book = findedBooks.stream()
                .filter(findedBook -> !findedBook.isUpdatedFromUI())
                .filter(findedBook -> findedBook.getAuthors() != null)
                .filter(findedBook -> findedBook.getAuthors().equals(authors)).findFirst().orElse(new Book());

        createGeneralFields(book, bookRow);
        creatBookAuthors(book, authors);
        if (bookRow.getVolumes() != null) {
            createCompleteWork(book, bookRow);
        }

        return book;
    }

    private void createGeneralFields(final Book book, final BookRow bookRow) {
        book.setName(bookRow.getName());
        book.setVolume(bookRow.getVolume());
        book.setYearOfPublication(bookRow.getYearOfPublication());
    }

    private Set<BookAuthor> getBookAuthor(final BookRow bookRow) {
        final String[] authorArray = Optional.ofNullable(bookRow.getAuthor())
                .orElse("").split(AUTHOR_SEPARATOR, -1);
        return Stream.of(authorArray)
                    .filter(authorName -> (authorName != null && !authorName.isEmpty()))
                    .map(authorName -> getOrCreateAuthor(authorName))
                    .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private void creatBookAuthors(final Book book, final Set<BookAuthor> authors) {
        if (book.getAuthors() == null) {
            book.setAuthors(new HashSet<>());
        }
        if (!book.getAuthors().equals(authors)) {
            book.getAuthors().clear();
            book.setAuthors(authors);
        }
    }

    private BookAuthor getOrCreateAuthor(final String authorName) {
        final BookAuthor author = new BookAuthor();
        author.setLastName(authorName);
        return author;
    }

    private void createCompleteWork(final Book book, final BookRow bookRow) {
        CompleteWork completeWork = book.getCompleteWork();
        if (completeWork == null) {
            completeWork = new CompleteWork();
        }
        completeWork = updateCompleteWork(completeWork, bookRow);
        book.setCompleteWork(completeWork);
    }

    private CompleteWork updateCompleteWork(final CompleteWork completeWork, final BookRow bookRow) {
        completeWork.setTotalVolumes(Integer.valueOf(bookRow.getVolumes()));

        completeWork.setFirstVolumeInYear(bookRow.getFirstVolumeInYear());
        completeWork.setLastVolumeInYear(bookRow.getLastVolumeInYear());

        return completeWork;
    }
}

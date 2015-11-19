package com.alextim.bookshelf;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;

public class Utilities {
    private static final Function<BookAuthor, String> BY_AUTHOR_LAST_NAME = author -> author.getLastName();
    private static final CharSequence SEPARATOR = ",";

    public static final BiFunction<Collection<Book>, Set<BookAuthor>, Collection<Book>> FILTER_BY_AUTHOR_NAMES_BI_FUNCTION = Utilities::filterByAuthorNames;
    public static final Function<Book, String> AUTHOR_YEAR_PUBLICATION_FUNCTION = Utilities::getKeyByAuthorAndYearPublication;
    public static final Function<Book, Long> AUTHOR_ID_FUNCTION = Utilities::getKeyByAuthorId;

    private static List<Book> filterByAuthorNames(final Collection<Book> books, final Set<BookAuthor> authors) {
        final String authorNames = authors.stream()
                .map(author -> BY_AUTHOR_LAST_NAME.apply(author))
                .collect(Collectors.joining(SEPARATOR));

        return books
                .stream()
                .filter(book -> {
                    return book.getAuthors().stream()
                            .map(author -> BY_AUTHOR_LAST_NAME.apply(author))
                            .collect(Collectors.joining(SEPARATOR))
                            .equals(authorNames);
                }).collect(Collectors.toList());
    }

    private static String getKeyByAuthorAndYearPublication(final Book book) {
        final String authorNames = book.getAuthors()
            .stream()
            .map(author -> BY_AUTHOR_LAST_NAME.apply(author))
            .collect(Collectors.joining(SEPARATOR));

        return String.format("%s_%s", authorNames, book.getYearOfPublication());
    }

    private static Long getKeyByAuthorId(final Book book) {
        return book.getAuthors().stream().findFirst().get().getId();
    }
}

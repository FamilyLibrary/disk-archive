package com.alextim.bookshelf.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;

public interface IBookService {
    Map<Object, List<Integer>> getAllAbsentBooks(Collection<Book> books, Function<Book, Object> function);
    Map<Object, List<Integer>> getAllAbsentBooks(Collection<Book> books, Set<BookAuthor> authors, Function<Book, Object> function);

    Collection<Book> uploadBookFile();

    void insert(Collection<Book> books);
}

package com.alextim.bookshelf.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import com.alextim.bookshelf.entity.Book;

public interface IBookService {
    Map<Object, List<Integer>> getAllAbsentBooks(Function<Book, Object> function);
    Map<Object, List<Integer>> getAllAbsentBooks(String firstAuthorName, String lastAuthorName, Function<Book, Object> function);

    Collection<Book> uploadBookFile();
}

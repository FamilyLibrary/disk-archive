package com.alextim.bookshelf.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.service.impl.AbsentVolumesResult;

public interface IBookService {
    List<AbsentVolumesResult> getAllAbsentBooks(Function<Book, Object> function);
    List<Integer> getAllAbsentBooks(String firstAuthorName, String lastAuthorName, Function<Book, Object> function);

    Collection<Book> uploadBookFile();
}

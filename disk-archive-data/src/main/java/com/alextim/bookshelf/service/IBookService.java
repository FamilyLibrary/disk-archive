package com.alextim.bookshelf.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Direction;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.BookAuthor;

public interface IBookService {
    <T> Map<T, List<Integer>> getAllAbsentBooks(Collection<Book> books, final Function<Book, T> function);
    <T> Map<T, List<Integer>> getAllAbsentBooks(Collection<Book> books, Set<BookAuthor> authors, Function<Book, T> function);

    Collection<Book> uploadBookFile();

    List<Book> save(Collection<Book> books);
    void delete(Book book);

    Book getById(Long id);
    Collection<Book> findAll();

    Page<Book> findPage(int page, int limit, String sort, Direction dir);
}

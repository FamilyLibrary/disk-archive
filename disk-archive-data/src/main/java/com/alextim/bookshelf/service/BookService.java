package com.alextim.bookshelf.service;

import java.util.List;

import com.alextim.bookshelf.entity.Book;
import com.alextim.bookshelf.entity.CompleteWork;
import com.alextim.bookshelf.service.impl.AbsentVolumesResult;
import com.alextim.diskarchive.entity.Author;

public interface BookService {
    List<AbsentVolumesResult> getAllAbsentBooks();
    List<Integer> getAllAbsentBooks(String firstAuthorName, String lastAuthorName);

    Book findBook(Integer volume, Integer yearOfPublication, CompleteWork completeWork, Author author);
}

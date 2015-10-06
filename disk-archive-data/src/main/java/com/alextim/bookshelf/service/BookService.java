package com.alextim.bookshelf.service;

import java.util.List;

import com.alextim.bookshelf.service.impl.AbsentVolumesResult;

public interface BookService {
    List<AbsentVolumesResult> getAllAbsentBooks();
    List<Integer> getAllAbsentBooks(String firstAuthorName, String lastAuthorName);
}

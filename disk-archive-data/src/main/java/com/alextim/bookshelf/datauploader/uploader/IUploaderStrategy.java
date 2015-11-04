package com.alextim.bookshelf.datauploader.uploader;

import java.util.Collection;

import com.alextim.bookshelf.entity.Book;


public interface IUploaderStrategy {
    Collection<Book> load();
}

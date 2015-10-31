package com.alextim.bookshelf.datauploader.uploader;

import java.util.Collection;

import com.alextim.bookshelf.entity.Book;


public interface IUploader {
    Collection<Book> load();
}

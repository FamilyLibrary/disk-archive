package com.alextim.bookshelf.datauploader.uploader.impl;

import java.util.Collection;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class UploaderContext {
    IUploaderStrategy strategy;

    public UploaderContext(IUploaderStrategy strategy) {
        this.strategy = strategy;
    }

    public Collection<Book> perform() {
        return strategy.load();
    }
}

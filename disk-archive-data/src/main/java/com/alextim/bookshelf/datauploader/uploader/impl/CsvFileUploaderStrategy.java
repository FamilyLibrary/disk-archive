package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.File;
import java.util.Collection;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class CsvFileUploaderStrategy implements IUploaderStrategy {
    private File file;

    public CsvFileUploaderStrategy(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File: Illegal argument. File can not be null value");
        }
        this.file = file;
    }

    @Override
    public Collection<Book> load() {
        throw new UnsupportedOperationException("Not Implemented");
    }

}

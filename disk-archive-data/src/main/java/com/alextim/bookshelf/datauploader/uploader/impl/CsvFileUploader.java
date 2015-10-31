package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.File;
import java.util.Collection;

import com.alextim.bookshelf.datauploader.uploader.IUploader;
import com.alextim.bookshelf.entity.Book;

public class CsvFileUploader implements IUploader {
    private File file;

    public CsvFileUploader(final File file) {
        this.file = file;
    }

    @Override
    public Collection<Book> load() {
        throw new UnsupportedOperationException("Not Implemented");
    }

}

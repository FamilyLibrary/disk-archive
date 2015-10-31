package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.File;
import java.util.Collection;

import com.alextim.bookshelf.datauploader.uploader.IUploader;
import com.alextim.bookshelf.entity.Book;

public class XlsFileUploader implements IUploader {
    private File file;

    public XlsFileUploader(final File file) {
        this.file = file;
    }

    @Override
    public Collection<Book> load() {
        throw new UnsupportedOperationException("Not Implemented");
    }

}

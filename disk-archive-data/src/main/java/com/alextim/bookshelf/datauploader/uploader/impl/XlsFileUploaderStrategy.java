package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.File;
import java.util.Collection;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class XlsFileUploaderStrategy implements IUploaderStrategy {
    private File file;

    public XlsFileUploaderStrategy(final File file) {
        this.file = file;
    }

    @Override
    public Collection<Book> load() {
        throw new UnsupportedOperationException("Not Implemented");
    }

}

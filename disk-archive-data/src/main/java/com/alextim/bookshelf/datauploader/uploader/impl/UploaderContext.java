package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class UploaderContext {
    private static final Logger LOG = Logger.getLogger(UploaderContext.class);

    private IUploaderStrategy strategy;

    public UploaderContext(IUploaderStrategy strategy) {
        this.strategy = strategy;
    }

    public Collection<Book> perform() {
        List<Book> books = new ArrayList<>();
        try {
            return strategy.load();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return books;
    }
}

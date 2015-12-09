package com.alextim.bookshelf.datauploader.uploader;

import java.io.IOException;
import java.util.Collection;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.alextim.bookshelf.entity.Book;


public interface IUploaderStrategy {
    Collection<Book> load() throws IOException, InvalidFormatException;
}

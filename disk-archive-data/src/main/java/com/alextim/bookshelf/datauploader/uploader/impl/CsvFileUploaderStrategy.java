package com.alextim.bookshelf.datauploader.uploader.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;
import com.alextim.bookshelf.entity.Book;

public class CsvFileUploaderStrategy extends AbstractCsvUploaderStrategy implements IUploaderStrategy {
    private static final Logger LOG = Logger.getLogger(CsvFileUploaderStrategy.class);

    private static final String FILE_ENCODING = "UTF-8";

    private final File file;

    public CsvFileUploaderStrategy(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File: Illegal argument. File can not be null value");
        }
        this.file = file;
    }

    @Override
    public Collection<Book> load() throws IOException {
        final Collection<Book> books = new ArrayList<Book>();

        final Path path = file.toPath();
        try (final InputStream in = Files.newInputStream(path, StandardOpenOption.READ);
             final BufferedReader reader = new BufferedReader(new InputStreamReader(in, FILE_ENCODING))) {

            String line = reader.readLine(); //Skip first line
            while ((line = reader.readLine()) != null) {
                try {
                    validator.validate(line);
                    books.add(mapToBook(line));
                } catch (ValidationException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }
        return books;
    }
}

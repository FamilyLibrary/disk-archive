package com.alextim.bookshelf.datauploader.uploader.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.NAME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;
import com.alextim.bookshelf.entity.Book;

public class XlsFileUploaderStrategy extends AbstractUploaderStrategy implements IUploaderStrategy {
    private static final Logger LOG = Logger.getLogger(XlsFileUploaderStrategy.class);

    @Resource
    private IBookValidator<Row> validator;

    private final File file;

    public XlsFileUploaderStrategy(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File: Illegal argument. File can not be null value");
        }
        this.file = file;
    }

    private Book mapToBook(final Row row) {
        return convertToBook(createRow(row::getCell));
    }

    @Override
    public Collection<Book> load() {
        final Collection<Book> books = new ArrayList<Book>();

        try (final Workbook workbook = WorkbookFactory.create(file)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                    final Row row = sheet.getRow(rowIndex);
                    try {
                        validator.validate(row);
                        books.add(mapToBook(row));
                    } catch (ValidationException e) {
                        LOG.error(e.getMessage(), e);
                    }
                }
            }
        } catch (IOException | InvalidFormatException | EncryptedDocumentException e) {
            LOG.error(e.getMessage(), e);
        }

        return books;
    }
    
    private BookRow createRow(final Function<Integer, Cell> getFunc) {
        final BookRow bookRow = new BookRow();

        updateStringField(bookRow::setAuthor, AUTHOR.apply(getFunc));
        updateStringField(bookRow::setName, NAME.apply(getFunc));
        updateIntegerField(bookRow::setVolume, VOLUME.apply(getFunc));
        updateIntegerField(bookRow::setVolumes, VOLUMES.apply(getFunc));
        updateIntegerField(bookRow::setYearOfPublication, YEAR_OF_PUBLICATION.apply(getFunc));
        updateIntegerField(bookRow::setFirstVolumeInYear, FIRST_VOLUME_IN_YEAR.apply(getFunc));
        updateIntegerField(bookRow::setLastVolumeInYear, LAST_VOLUME_IN_YEAR.apply(getFunc));

        return bookRow;
    }
 
    private void updateStringField(final Consumer<String> consumer, Cell cell) {
        if (cell != null) {
            consumer.accept(cell.getStringCellValue());
        }
    }
    private void updateIntegerField(final Consumer<Integer> consumer, Cell cell) {
        if (cell != null) {
            consumer.accept(Double.valueOf(cell.getNumericCellValue()).intValue());
        }
    }
}

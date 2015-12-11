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
import java.util.function.Function;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alextim.bookshelf.datauploader.uploader.IUploaderStrategy;
import com.alextim.bookshelf.entity.Book;

public class XlsFileUploaderStrategy extends AbstractUploaderStrategy implements IUploaderStrategy {
    private final File file;

    public XlsFileUploaderStrategy(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("File: Illegal argument. File can not be null value");
        }
        this.file = file;
    }

    private Book mapToBook(final Row row) {
        Function<Integer, Cell> getFunc = row::getCell;
        return convertToBook(createRow(getFunc));
    }

    @Override
    public Collection<Book> load() throws IOException, InvalidFormatException {
        final Collection<Book> books = new ArrayList<Book>();

        try (final Workbook workbook = new XSSFWorkbook(file)) {
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                final Sheet sheet = workbook.getSheetAt(sheetIndex);
                for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                    final Row row = sheet.getRow(rowIndex);
                    books.add(mapToBook(row));
                }
            }
        }

        return books;
    }
    
    private BookRow createRow(final Function<Integer, Cell> getFunc) {
        final BookRow bookRow = new BookRow();

        bookRow.setAuthor(readAsString(AUTHOR.apply(getFunc)));
        bookRow.setName(readAsString(NAME.apply(getFunc)));
        bookRow.setVolume(readAsString(VOLUME.apply(getFunc)));
        bookRow.setVolumes(readAsString(VOLUMES.apply(getFunc)));
        bookRow.setYearOfPublication(readAsString(YEAR_OF_PUBLICATION.apply(getFunc)));
        bookRow.setFirstVolumeInYear(readAsString(FIRST_VOLUME_IN_YEAR.apply(getFunc)));
        bookRow.setLastVolumeInYear(readAsString(LAST_VOLUME_IN_YEAR.apply(getFunc)));

        return bookRow;
    }

    /*TODO: Should be refactored somehow */
    private String readAsString(Cell cell) {
        if (cell == null) {
            return null;
        }
        cell.setCellType(Cell.CELL_TYPE_STRING);
        return cell.getStringCellValue();
    }
}

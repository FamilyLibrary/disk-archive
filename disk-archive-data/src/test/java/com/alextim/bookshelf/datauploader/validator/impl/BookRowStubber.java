package com.alextim.bookshelf.datauploader.validator.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.NAME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;
import static org.mockito.Mockito.when;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.impl.BookRowStubber.Builder.Pair;

public class BookRowStubber {
    private static final String MSG_ILLEGAL_CELL_VALUE = "Cannot get a numeric value from a cell with type %s (Field %s)";

    private Pair<Object> author;
    private Pair<Object> name;
    private Pair<Object> volume;
    private Pair<Object> volumes;
    private Pair<Object> yearOfPublication;
    private Pair<Object> firstVolumeInYear;
    private Pair<Object> lastVolumeInYear;

    private BookRowStubber(Builder builder) {
        this.author = builder.author;
        this.yearOfPublication = builder.yearOfPublication;
        this.name = builder.name;
        this.volume = builder.volume;
        this.volumes = builder.volumes;
        this.firstVolumeInYear = builder.firstVolumeInYear;
        this.lastVolumeInYear = builder.lastVolumeInYear;
    }

    public void stub(Function<Integer, Cell> indexFunction) {
        stubField(indexFunction, AUTHOR, author);
        stubField(indexFunction, NAME, name);
        stubField(indexFunction, YEAR_OF_PUBLICATION, yearOfPublication);
        stubField(indexFunction, VOLUME, volume);
        stubField(indexFunction, VOLUMES, volumes);
        stubField(indexFunction, FIRST_VOLUME_IN_YEAR, firstVolumeInYear);
        stubField(indexFunction, LAST_VOLUME_IN_YEAR, lastVolumeInYear);
    }

    private <T> void stubField(Function<Integer, Cell> indexFunction, BookField field, Pair<Object> pair) {
        if (pair != null) {
            final Cell cell = pair.cell;
            final Object value = pair.value;

            when(field.apply(indexFunction)).thenReturn(cell);

            if (cell != null) {
                stubByType(cell, field, value);
            }
        }
    }

    private void stubByType(Cell cell, BookField field, Object value) {
        final int cellType = cell.getCellType();

        if (cellType != Cell.CELL_TYPE_STRING && value instanceof String) {
            when(cell.getNumericCellValue()).thenThrow(new IllegalStateException(
                    String.format(MSG_ILLEGAL_CELL_VALUE, cellType, field)));
        } else if (cellType == Cell.CELL_TYPE_NUMERIC) {
            when(cell.getNumericCellValue()).thenReturn((double)value);
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            when(cell.getStringCellValue()).thenReturn((String)value);
        } else {
            throw new IllegalArgumentException("Unsupported cell type");
        }
    }

    public static final class Builder {
        private Pair<Object> author;
        private Pair<Object> name;
        private Pair<Object> volume;
        private Pair<Object> volumes;
        private Pair<Object> yearOfPublication;
        private Pair<Object> firstVolumeInYear;
        private Pair<Object> lastVolumeInYear;

        public Builder setAuthor(Pair<Object> author) {
            this.author = author;
            return this;
        }
    
        public Builder setName(Pair<Object> name) {
            this.name = name;
            return this;
        }
    
        public Builder setVolume(Pair<Object> volume) {
            this.volume = volume;
            return this;
        }
    
        public Builder setVolumes(Pair<Object> volumes) {
            this.volumes = volumes;
            return this;
        }
    
        public Builder setYearOfPublication(Pair<Object> yearOfPublication) {
            this.yearOfPublication = yearOfPublication;
            return this;
        }
    
        public Builder setFirstVolumeInYear(Pair<Object> firstVolumeInYear) {
            this.firstVolumeInYear = firstVolumeInYear;
            return this;
        }
    
        public Builder setLastVolumeInYear(Pair<Object> lastVolumeInYear) {
            this.lastVolumeInYear = lastVolumeInYear;
            return this;
        }

        public static Pair<Object> create(Cell cell, Object value) {
            Pair<Object> pair = new Pair<>();
            pair.cell = cell;
            pair.value = value;
            return pair;
        }
    
        public synchronized BookRowStubber build() {
            return new BookRowStubber(this);
        }

        static class Pair<R> {
            Cell cell;
            R value;
        };
    }
}
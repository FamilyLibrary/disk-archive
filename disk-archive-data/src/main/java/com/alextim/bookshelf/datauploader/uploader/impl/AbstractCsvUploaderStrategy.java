package com.alextim.bookshelf.datauploader.uploader.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.NAME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.alextim.bookshelf.entity.Book;

public class AbstractCsvUploaderStrategy extends AbstractUploaderStrategy {
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    protected Book mapToBook(final String line) {
        final List<String> parts = Stream
            .of(line.split(REG_EXP_SEPARATOR, -1))
            .collect(Collectors.toList());

        return convertToBook(createRow(parts::get));
    }

    protected BookRow createRow(final Function<Integer, String> getFunc) {
        final BookRow bookRow = new BookRow();

        bookRow.setAuthor(AUTHOR.apply(getFunc));
        bookRow.setName(NAME.apply(getFunc));
        bookRow.setVolume(VOLUME.apply(getFunc));
        bookRow.setVolumes(VOLUMES.apply(getFunc));
        bookRow.setYearOfPublication(YEAR_OF_PUBLICATION.apply(getFunc));
        bookRow.setFirstVolumeInYear(FIRST_VOLUME_IN_YEAR.apply(getFunc));
        bookRow.setLastVolumeInYear(LAST_VOLUME_IN_YEAR.apply(getFunc));

        return bookRow;
    }
}

package com.alextim.bookshelf.datauploader.uploader.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.NAME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.entity.Book;

public class AbstractCsvUploaderStrategy extends AbstractUploaderStrategy {
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    @Resource
    protected IBookValidator<String> validator;

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
        updateIntegerField(bookRow::setVolume, VOLUME.apply(getFunc));
        updateIntegerField(bookRow::setVolumes, VOLUMES.apply(getFunc));
        updateIntegerField(bookRow::setYearOfPublication, YEAR_OF_PUBLICATION.apply(getFunc));
        updateIntegerField(bookRow::setFirstVolumeInYear, FIRST_VOLUME_IN_YEAR.apply(getFunc));
        updateIntegerField(bookRow::setLastVolumeInYear, LAST_VOLUME_IN_YEAR.apply(getFunc));

        return bookRow;
    }

    private void updateIntegerField(final Consumer<Integer> consumer, String value) {
        if (StringUtils.isNotEmpty(value)) {
            consumer.accept(Integer.valueOf(value));
        }
    }
}

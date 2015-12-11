package com.alextim.bookshelf.datauploader.validator.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;

import java.util.function.Function;

import org.apache.commons.lang.StringUtils;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

public class CsvBookValidator implements IBookValidator<String> {
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    private static final int DATA_SIZE = 8;

    private static final CharSequence INVALID_DATA_PREFIX = "Invalid data: ";
    private static final String MSG_INVALID_DATA_SIZE = 
            String.join(" ", INVALID_DATA_PREFIX, "Record must contains ", Integer.toString(DATA_SIZE), " parts");
    private static final String MSG_DATA_EMPTY_OR_NULL_VALUE = String.join(" ", INVALID_DATA_PREFIX, "Parameter is empty or null value");
    private static final String MSG_ABSENT_AUTHORS = String.join(" ", INVALID_DATA_PREFIX, "Field authors is required");
    private static final String MSG_ABSENT_YEAR_OF_PUBLICATION = String.join(" ", INVALID_DATA_PREFIX, "Field 'year of publication' is required");

    @Override
    public boolean validate(final String data) throws ValidationException {
        if (StringUtils.isEmpty(data)) {
            throw new IllegalArgumentException(MSG_DATA_EMPTY_OR_NULL_VALUE);
        }

        final String[] parts = data.split(REG_EXP_SEPARATOR, -1);
        if (parts.length != DATA_SIZE) {
            throw new ValidationException(MSG_INVALID_DATA_SIZE);
        }

        final Function<Integer, String> partIndexFunction = (index) -> parts[index];

        validateRequiredFields(partIndexFunction);
        validateOptinalFields(partIndexFunction);

        return true;
    }

    private void validateRequiredFields(final Function<Integer, String> partIndexFunction) throws ValidationException {
        final String authors = AUTHOR.apply(partIndexFunction);
        if (StringUtils.isEmpty(authors)) {
            throw new ValidationException(MSG_ABSENT_AUTHORS, AUTHOR);
        } 

        final String yearOfPublication = YEAR_OF_PUBLICATION.apply(partIndexFunction);
        if (StringUtils.isEmpty(yearOfPublication)) {
            throw new ValidationException(MSG_ABSENT_YEAR_OF_PUBLICATION, YEAR_OF_PUBLICATION);
        }
    }

    private void validateOptinalFields(final Function<Integer, String> partIndexFunction) throws ValidationException {
        validateNumericField(VOLUME, partIndexFunction);
        validateNumericField(VOLUMES, partIndexFunction);
        validateNumericField(YEAR_OF_PUBLICATION, partIndexFunction);
        validateNumericField(FIRST_VOLUME_IN_YEAR, partIndexFunction);
        validateNumericField(LAST_VOLUME_IN_YEAR, partIndexFunction);
    }

    private void validateNumericField(final BookField field,
            final Function<Integer, String> partIndexFunction) throws ValidationException {

        final String value = field.apply(partIndexFunction);
        try {
            if (StringUtils.isEmpty(value)) {
                return;
            }
            Integer.parseInt(value);
        } catch (NumberFormatException e) {
            throw new ValidationException(e, field);
        }
        
    }
}

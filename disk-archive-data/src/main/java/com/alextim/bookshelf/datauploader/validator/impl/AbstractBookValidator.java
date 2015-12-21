package com.alextim.bookshelf.datauploader.validator.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;

import java.util.function.Function;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.FirstFieldGreatThenSecondFieldException;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

public abstract class AbstractBookValidator<R, C> implements IBookValidator<R> {
    protected static final String INVALID_DATA_PREFIX = "Invalid data:";
    protected static final String MSG_ABSENT_AUTHORS = String.join(" ", INVALID_DATA_PREFIX, "Field authors is required");
    protected static final String MSG_ABSENT_YEAR_OF_PUBLICATION = String.join(" ", INVALID_DATA_PREFIX, "Field 'year of publication' is required");
    protected static final String MSG_FIRST_FIELD_MUST_BE_GREAT_THEN_SECOND_FIELD = 
            String.join(" ", INVALID_DATA_PREFIX, "The first field must be great or equal then the second field");

    protected void validateRequiredFields(final Function<Integer, C> indexFunction) throws ValidationException {
        final C authors = AUTHOR.apply(indexFunction);
        if (checkIfEmpty(authors)) {
            throw new ValidationException(MSG_ABSENT_AUTHORS, AUTHOR);
        } 

        final C yearOfPublication = YEAR_OF_PUBLICATION.apply(indexFunction);
        if (checkIfEmpty(yearOfPublication)) {
            throw new ValidationException(MSG_ABSENT_YEAR_OF_PUBLICATION, YEAR_OF_PUBLICATION);
        }
    }

    protected void validateOptinalFields(final Function<Integer, C> indexFunction) throws ValidationException {
        validateNumericField(VOLUME, indexFunction);
        validateNumericField(VOLUMES, indexFunction);
        validateNumericField(YEAR_OF_PUBLICATION, indexFunction);
        validateNumericField(FIRST_VOLUME_IN_YEAR, indexFunction);
        validateNumericField(LAST_VOLUME_IN_YEAR, indexFunction);

        validateFirstGreaterOrEqualThenSecondField(VOLUME, VOLUMES, indexFunction);
        validateFirstGreaterOrEqualThenSecondField(FIRST_VOLUME_IN_YEAR, LAST_VOLUME_IN_YEAR, indexFunction);
    }

    private void validateFirstGreaterOrEqualThenSecondField(final BookField firstField, final BookField secondField,
            final Function<Integer, C> indexFunction) throws ValidationException {

        Number firstValue = parseNumber(firstField.apply(indexFunction));
        Number secondValue = parseNumber(secondField.apply(indexFunction));

        if (firstValue.intValue() > secondValue.intValue()) {
            throw new FirstFieldGreatThenSecondFieldException(MSG_FIRST_FIELD_MUST_BE_GREAT_THEN_SECOND_FIELD, firstField, secondField);
        }
    }

    protected abstract void validateNumericField(BookField field, Function<Integer, C> indexFunction) throws ValidationException;

    protected abstract Number parseNumber(C value);
    protected abstract boolean checkIfEmpty(C value);
}

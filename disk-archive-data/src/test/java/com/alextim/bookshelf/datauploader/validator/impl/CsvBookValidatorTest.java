package com.alextim.bookshelf.datauploader.validator.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.FirstFieldGreatThenSecondFieldException;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

public class CsvBookValidatorTest {
    private static final String BOOK_WITHOUT_AUTHORS = ",Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,,";
    private static final String BOOK_WITHOUT_YOP = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,,,,";

    private static final String INVALID_CSV_STRING = ",1,,,,";

    private static final String BOOK_WITH_INVALID_YOP = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38,2,A1979A,,,";
    private static final String BOOK_WITH_INVALID_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38A,2,1979,,,";
    private static final String BOOK_WITH_INVALID_VOLUMES = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38,AAA,1979,,,";
    private static final String BOOK_WITH_INVALID_FIRST_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,!1A,,";
    private static final String BOOK_WITH_INVALID_LAST_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,!1A,";
    private static final String BOOK_WITH_VOLUME_GREAT_THEN_VOLUMES = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,85,2,1979,,,";
    private static final String BOOK_WITH_FIRST_VOLUME_IN_YEAR_GREAT_THEN_LAST_VOLUME_IN_YEAR = 
            "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,88,12,";

    private static final String VALID_BOOK = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,,";

    private IBookValidator<String> validator;

    @Before
    public void setUp() {
        validator = new CsvBookValidator();  
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForEmptyString() throws ValidationException {
        validator.validate("");
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForNullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test
    public void shouldThrowValidationExceptionForInvalidCSVString() {
        try {
            validate(INVALID_CSV_STRING);
        } catch (final ValidationException e) {
            assertNull(e.getField());
        }
    }

    @Test
    public void shouldThrowValidationExceptionForRecordWithoutAuthors() {
        validateAndCheckField(BOOK_WITHOUT_AUTHORS, AUTHOR);
    }

    @Test
    public void shouldThrowValidationExceptionForRecordWithoutYOP() {
        validateAndCheckField(BOOK_WITHOUT_YOP, YEAR_OF_PUBLICATION);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericYOP() {
        validateAndCheckValidationException(BOOK_WITH_INVALID_YOP, YEAR_OF_PUBLICATION, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolume() {
        validateAndCheckValidationException(BOOK_WITH_INVALID_VOLUME, VOLUME, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolumes() {
        validateAndCheckValidationException(BOOK_WITH_INVALID_VOLUMES, VOLUMES, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericFirstVolume() {
        validateAndCheckValidationException(BOOK_WITH_INVALID_FIRST_VOLUME, FIRST_VOLUME_IN_YEAR, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericLastVolume() {
        validateAndCheckValidationException(BOOK_WITH_INVALID_LAST_VOLUME, LAST_VOLUME_IN_YEAR, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionIfVolumeGreatThenVolumes() throws ValidationException {
        validateAndCheckFields(BOOK_WITH_VOLUME_GREAT_THEN_VOLUMES, VOLUME, VOLUMES);
    }

    @Test
    public void shouldThrowValidationExceptionIfFirstVolumeInYearGreatThenLastVolumeInYear() throws ValidationException {
        validateAndCheckFields(BOOK_WITH_FIRST_VOLUME_IN_YEAR_GREAT_THEN_LAST_VOLUME_IN_YEAR, FIRST_VOLUME_IN_YEAR, LAST_VOLUME_IN_YEAR);
    }

    @Test
    public void shouldValidateBook() throws ValidationException {
        assertTrue(validator.validate(VALID_BOOK));
    }

    private void validateAndCheckValidationException(final String bookRecord, final BookField field, final Class<? extends Throwable> clazz) {
        try {
            validate(bookRecord);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
            assertTrue(clazz.equals(e.getCause().getClass()));
        }
    }

    private void validateAndCheckField(final String bookRecord, final BookField field) {
        try {
            validate(bookRecord);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
        }
    }

    private void validateAndCheckFields(final String bookRecord, BookField firstField, BookField secondField) throws ValidationException {
        try {
            validate(bookRecord);
        } catch (final FirstFieldGreatThenSecondFieldException e) {
            assertEquals(firstField, e.getField());
            assertEquals(secondField, e.getSecondField());
        }
    }

    private void validate(final String bookRecord) throws ValidationException {
        validator.validate(bookRecord);
        fail("The method must throw an exception");
    }
}

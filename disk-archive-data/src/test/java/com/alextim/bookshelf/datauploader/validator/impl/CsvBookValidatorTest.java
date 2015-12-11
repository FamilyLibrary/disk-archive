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
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

@RunWith(MockitoJUnitRunner.class)
public class CsvBookValidatorTest {
    private IBookValidator<String> validator;

    private static final String BOOK_WITHOUT_AUTHORS = ",Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,,";
    private static final String BOOK_WITHOUT_YOP = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,,,,";

    private static final String INVALID_CSV_STRING = ",1,,,,";

    private static final String BOOK_WITH_INVALID_YOP = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38,2,A1979A,,,";
    private static final String BOOK_WITH_INVALID_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38A,2,1979,,,";
    private static final String BOOK_WITH_INVALID_VOLUMES = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,38,AAA,1979,,,";
    private static final String BOOK_WITH_INVALID_FIRST_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,!1A,,";
    private static final String BOOK_WITH_INVALID_LAST_VOLUME = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,!1A,";

    private static final String VALID_BOOK = "Мигель де Сервантес Сааведра,Хитроумный Идальго Дон Кихот Ламанчский,1,2,1979,,,";

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
    public void shouldThrowValidationExceptionForInvalidCSVString() throws ValidationException {
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
    public void shouldThrowValidationExceptionForRecordWithoutYOP() throws ValidationException {
        validateAndCheckField(BOOK_WITHOUT_YOP, YEAR_OF_PUBLICATION);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericYOP() throws ValidationException {
        validateAndCheckValidationException(BOOK_WITH_INVALID_YOP, YEAR_OF_PUBLICATION, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolume() throws ValidationException {
        validateAndCheckValidationException(BOOK_WITH_INVALID_VOLUME, VOLUME, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolumes() throws ValidationException {
        validateAndCheckValidationException(BOOK_WITH_INVALID_VOLUMES, VOLUMES, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericFirstVolume() throws ValidationException {
        validateAndCheckValidationException(BOOK_WITH_INVALID_FIRST_VOLUME, FIRST_VOLUME_IN_YEAR, NumberFormatException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericLastVolume() throws ValidationException {
        validateAndCheckValidationException(BOOK_WITH_INVALID_LAST_VOLUME, LAST_VOLUME_IN_YEAR, NumberFormatException.class);
    }

    @Test
    public void shouldValidateBook() throws ValidationException {
        assertTrue(validator.validate(VALID_BOOK));
    }
    
    private void validateAndCheckValidationException(String bookRecord, BookField field, Class<? extends Throwable> clazz) {
        try {
            validate(bookRecord);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
            assertTrue(clazz.equals(e.getCause().getClass()));
        }
    }

    private void validateAndCheckField(String bookRecord, BookField field) {
        try {
            validate(bookRecord);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
        }
    }

    private void validate(final String bookRecord) throws ValidationException {
        validator.validate(bookRecord);
        fail("The method must throw an exception");
    }
}

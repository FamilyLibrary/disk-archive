package com.alextim.bookshelf.datauploader.validator.impl;

import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.AUTHOR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.FIRST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.LAST_VOLUME_IN_YEAR;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUME;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.VOLUMES;
import static com.alextim.bookshelf.datauploader.uploader.impl.BookField.YEAR_OF_PUBLICATION;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.FirstFieldGreatThenSecondFieldException;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;
import com.alextim.bookshelf.datauploader.validator.impl.BookRowStubber.Builder;

@RunWith(MockitoJUnitRunner.class)
public class XlsBookValidatorTest {
    @Mock
    private Row row;

    @Mock
    private Cell authors;
    @Mock
    private Cell name;
    @Mock
    private Cell volume;
    @Mock
    private Cell volumes;
    @Mock
    private Cell yof;
    @Mock
    private Cell firstVolumeInYear;
    @Mock
    private Cell lastVolumeInYear;

    private Function<Integer, Cell> indexFunction;
    private Builder builder;

    @InjectMocks
    private IBookValidator<Row> validator = new XlsBookValidator();


    @Before
    public void setUp() { 
        indexFunction = row::getCell;

        builder = new Builder();
        builder
            .setAuthor(Builder.create(authors, "Мигель де Сервантес Сааведра"))
            .setName(Builder.create(name, "Хитроумный Идальго Дон Кихот Ламанчский"))
            .setVolume(Builder.create(volume, 1D))
            .setVolumes(Builder.create(volumes, 2D))
            .setYearOfPublication(Builder.create(yof, 1979D));
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionForNullValue() throws ValidationException {
        validator.validate(null);
    }

    @Test
    public void shouldThrowValidationExceptionForRecordWithoutAuthors() {
        BookRowStubber stubber = builder
                .setAuthor(null)
                .build();

        validateAndCheckField(row, AUTHOR, stubber);
    }

    @Test
    public void shouldThrowValidationExceptionForRecordWithoutYOP() {
        BookRowStubber stubber = builder
                .setYearOfPublication(null)
                .build();

        validateAndCheckField(row, YEAR_OF_PUBLICATION, stubber);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericYOP() {
        BookRowStubber stubber = builder
                .setYearOfPublication(Builder.create(yof, "A1979A"))
                .build();

        validateAndCheckValidationException(row, YEAR_OF_PUBLICATION, stubber, IllegalStateException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolume() {
        BookRowStubber stubber = builder
                .setVolume(Builder.create(volume, "38A"))
                .build();

        validateAndCheckValidationException(row, VOLUME, stubber, IllegalStateException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericVolumes() {
        BookRowStubber stubber = builder
                .setVolumes(Builder.create(volumes, "AAA"))
                .build();

        validateAndCheckValidationException(row, VOLUMES, stubber, IllegalStateException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericFirstVolume() {
        BookRowStubber stubber = builder
                .setFirstVolumeInYear(Builder.create(firstVolumeInYear, "!1A"))
                .build();

        validateAndCheckValidationException(row, FIRST_VOLUME_IN_YEAR, stubber, IllegalStateException.class);
    }

    @Test
    public void shouldThrowValidationExceptionForNonNumericLastVolume() {
        BookRowStubber stubber = builder
                .setLastVolumeInYear(Builder.create(lastVolumeInYear, "!1A"))
                .build();

        validateAndCheckValidationException(row, LAST_VOLUME_IN_YEAR, stubber, IllegalStateException.class);
    }

    @Test
    public void shouldThrowValidationExceptionIfVolumeGreatThenVolumes() throws ValidationException {
        BookRowStubber stubber = builder
                .setVolume(Builder.create(volume, 35D))
                .setVolumes(Builder.create(volumes, 1D))
                .build();
        validateAndCheckFields(row, VOLUME, VOLUMES, stubber);
    }

    @Test
    public void shouldThrowValidationExceptionIfFirstVolumeInYearGreatThenLastVolumeInYear() throws ValidationException {
        BookRowStubber stubber = builder
                .setFirstVolumeInYear(Builder.create(firstVolumeInYear, 35D))
                .setLastVolumeInYear(Builder.create(lastVolumeInYear, 1D))
                .build();
        validateAndCheckFields(row, FIRST_VOLUME_IN_YEAR, LAST_VOLUME_IN_YEAR, stubber);
    }

    @Test
    public void shouldValidateBook() throws ValidationException {
        builder.build().stub(indexFunction);
        assertTrue(validator.validate(row));
    }

    private void validateAndCheckValidationException(final Row bookRow, BookField field, BookRowStubber stubber, Class<? extends Throwable> clazz) {
        try {
            validate(bookRow, stubber);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
            assertTrue(clazz.equals(e.getCause().getClass()));
        }
    }

    private void validateAndCheckField(final Row bookRow, BookField field, BookRowStubber stubber) {
        try {
            validate(bookRow, stubber);
        } catch (final ValidationException e) {
            assertEquals(field, e.getField());
        }
    }

    private void validateAndCheckFields(final Row bookRow, BookField firstField, BookField secondField, BookRowStubber stubber) throws ValidationException {
        try {
            validate(bookRow, stubber);
        } catch (final FirstFieldGreatThenSecondFieldException e) {
            assertEquals(firstField, e.getField());
            assertEquals(secondField, e.getSecondField());
        }
    }

    private void validate(final Row bookRow, BookRowStubber stubber) throws ValidationException {
        stubber.stub(indexFunction);
        validator.validate(bookRow);

        fail("The method must throw an exception");
    }

}

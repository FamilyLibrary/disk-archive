package com.alextim.bookshelf.datauploader.validator.impl;

import java.util.function.Function;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

public class XlsBookValidator extends AbstractBookValidator<Row, Cell> {
    private static final String MSG_DATA_NULL_VALUE = String.join(" ", INVALID_DATA_PREFIX, "Parameter is null value");

    @Override
    public boolean validate(final Row row) throws ValidationException {
        if (row == null) {
            throw new IllegalArgumentException(MSG_DATA_NULL_VALUE);
        }

        final Function<Integer, Cell> indexFunction = row::getCell;

        validateRequiredFields(indexFunction);
        validateOptinalFields(indexFunction);

        return true;
    }

    @Override
    protected void validateNumericField(final BookField field,
            final Function<Integer, Cell> indexFunction) throws ValidationException {
        try {
            parseNumber(field.apply(indexFunction));
        } catch (IllegalStateException e) {
            throw new ValidationException(e, field);
        }
    }

    @Override
    protected Number parseNumber(Cell cell) {
        return cell == null ? 0D : cell.getNumericCellValue();
    }

    @Override
    protected boolean checkIfEmpty(Cell value) {
        return value == null;
    }
}

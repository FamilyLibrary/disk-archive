package com.alextim.bookshelf.datauploader.validator.impl;

import java.util.function.Function;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

@Component
public class CsvBookValidator extends AbstractBookValidator<String, String> {
    private static final String REG_EXP_SEPARATOR = ",(?=([^\"]|\"[^\"]*\")*$)";

    private static final int DATA_SIZE = 8;

    private static final String MSG_INVALID_DATA_SIZE = 
            String.join(" ", INVALID_DATA_PREFIX, "Record must contains ", Integer.toString(DATA_SIZE), " parts");
    private static final String MSG_DATA_EMPTY_OR_NULL_VALUE = String.join(" ", INVALID_DATA_PREFIX, "Parameter is empty or null value");

    @Override
    public boolean validate(final String data) throws ValidationException {
        if (StringUtils.isEmpty(data)) {
            throw new IllegalArgumentException(MSG_DATA_EMPTY_OR_NULL_VALUE);
        }

        final String[] parts = data.split(REG_EXP_SEPARATOR, -1);
        if (parts.length != DATA_SIZE) {
            throw new ValidationException(MSG_INVALID_DATA_SIZE);
        }

        final Function<Integer, String> indexFunction = (index) -> parts[index];

        validateRequiredFields(indexFunction);
        validateOptinalFields(indexFunction);

        return true;
    }

    @Override
    protected void validateNumericField(final BookField field,
            final Function<Integer, String> indexFunction) throws ValidationException {
        try {
            parseNumber(field.apply(indexFunction));
        } catch (NumberFormatException e) {
            throw new ValidationException(e, field);
        }
    }

    @Override
    protected Number parseNumber(String value) {
        return  StringUtils.isEmpty(value) ? 0 : Integer.parseInt(value);
    }

    @Override
    protected boolean checkIfEmpty(String value) {
        return StringUtils.isEmpty(value);
    }
}

package com.alextim.bookshelf.datauploader.validator.impl;

import org.apache.poi.ss.usermodel.Row;

import com.alextim.bookshelf.datauploader.validator.IBookValidator;
import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;

public class XlsBookValidator implements IBookValidator<Row> {
    @Override
    public boolean validate(final Row row) throws ValidationException {
        throw new UnsupportedOperationException("Not implemented");
    }

}

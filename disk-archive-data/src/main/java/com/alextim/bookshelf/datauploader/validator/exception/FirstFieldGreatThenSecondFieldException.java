package com.alextim.bookshelf.datauploader.validator.exception;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;

public class FirstFieldGreatThenSecondFieldException extends ValidationException {
    private static final long serialVersionUID = -1900566761801501623L;

    private final BookField secondField;

    public FirstFieldGreatThenSecondFieldException(String message, BookField firstField, BookField secondField) {
        super(message, firstField);
        this.secondField = secondField;
    }

    public BookField getSecondField() {
        return secondField;
    }
}

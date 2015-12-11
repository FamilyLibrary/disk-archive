package com.alextim.bookshelf.datauploader.validator.exception;

import com.alextim.bookshelf.datauploader.uploader.impl.BookField;

public class ValidationException extends Exception {
    private static final long serialVersionUID = -8331904046673935509L;

    private final BookField field;

    public ValidationException(final String message) {
        this(message, null);
    }

    public ValidationException(final String message, final BookField field) {
        super(message);
        this.field = field;
    }

    public ValidationException(Throwable cause, final BookField field) {
        super(cause);
        this.field = field;
    }

    public BookField getField() {
        return field;
    }
}

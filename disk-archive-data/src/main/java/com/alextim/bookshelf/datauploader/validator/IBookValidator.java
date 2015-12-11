package com.alextim.bookshelf.datauploader.validator;

import com.alextim.bookshelf.datauploader.validator.exception.ValidationException;


public interface IBookValidator<T> {
    boolean validate(T data) throws ValidationException;
}

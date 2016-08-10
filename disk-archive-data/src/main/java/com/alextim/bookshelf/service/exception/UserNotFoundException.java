package com.alextim.bookshelf.service.exception;

/**
 * Created by admin on 01.08.2016.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}

package com.softserve.edu.hypercinema.exceptions;

public class AccessViolationException extends ServiceException {

    public AccessViolationException() {
    }

    public AccessViolationException(String message) {
        super(message);
    }

}
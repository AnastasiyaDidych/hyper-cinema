package com.softserve.edu.hypercinema.exception;


import javax.validation.ValidationException;

public class ServiceException extends ValidationException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

}

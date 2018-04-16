package com.softserve.edu.hypercinema.exception;

public class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

}

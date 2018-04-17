package com.softserve.edu.hypercinema.exception;

import com.softserve.edu.hypercinema.exception.ServiceException;

public class HallAlreadyExistException extends ServiceException {

    public HallAlreadyExistException() {
    }

    public HallAlreadyExistException(String message) {
        super(message);
    }

}

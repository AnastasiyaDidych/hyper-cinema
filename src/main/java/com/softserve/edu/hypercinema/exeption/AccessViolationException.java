package com.softserve.edu.hypercinema.exeption;

public class AccessViolationException extends ServiceExeption {

    public AccessViolationException() {
    }

    public AccessViolationException(String message) {
        super(message);
    }
}

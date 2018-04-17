package com.softserve.edu.hypercinema.exception;

public class PriceNotFoundException extends RuntimeException {

    public PriceNotFoundException() {
    }

    public PriceNotFoundException(String message) {
        super(message);
    }
}

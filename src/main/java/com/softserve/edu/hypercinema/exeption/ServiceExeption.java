package com.softserve.edu.hypercinema.exeption;

public class ServiceExeption extends RuntimeException {

    public ServiceExeption() {
    }

    public ServiceExeption(String message) {
        super(message);
    }
}

package com.softserve.edu.hypercinema.exceptions;

public class EmptyList extends ServiceException {

    public EmptyList() {
    }

    public EmptyList(String message) {
        super(message);
    }

}

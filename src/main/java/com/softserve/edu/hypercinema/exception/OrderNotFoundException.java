package com.softserve.edu.hypercinema.exception;

public class OrderNotFoundException extends DatabaseItemNotFoundException {
    public OrderNotFoundException(){

    }

    public OrderNotFoundException (String mes){
        super(mes);
    }
}

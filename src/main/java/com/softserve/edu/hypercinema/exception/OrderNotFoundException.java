package com.softserve.edu.hypercinema.exception;

public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException(){

    }

    public OrderNotFoundException (String mes){
        super(mes);
    }
}

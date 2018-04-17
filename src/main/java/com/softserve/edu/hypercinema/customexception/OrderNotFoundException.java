package com.softserve.edu.hypercinema.customexception;

public class OrderNotFoundException extends OrderException {
    public OrderNotFoundException(){

    }

    public OrderNotFoundException (String mes){
        super(mes);
    }
}

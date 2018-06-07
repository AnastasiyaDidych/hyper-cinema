package com.softserve.edu.hypercinema.exception;

public class PaymentNotFoundException extends DatabaseItemNotFoundException {
    public PaymentNotFoundException(){

    }

    public PaymentNotFoundException (String mes){
        super(mes);
    }
}

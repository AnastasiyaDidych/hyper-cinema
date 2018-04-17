package com.softserve.edu.hypercinema.exception;

public class TicketNotFoundException extends ServiceException {

    public TicketNotFoundException(){
    }

    public TicketNotFoundException(String message){
        super(message);
    }
}

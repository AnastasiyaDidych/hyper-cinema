package com.softserve.edu.hypercinema.exception;

/**
 * Created by VR
 * 18:07, 16.04.2018
 */

public class TicketNotFoundException extends ServiceException {

    public TicketNotFoundException(){
    }

    public TicketNotFoundException(String message){
        super(message);
    }
}

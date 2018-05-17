package com.softserve.edu.hypercinema.exception;

public class TicketUnavaiableException extends ServiceException {

    public TicketUnavaiableException(){
    }

    public TicketUnavaiableException(String message){
        super(message);
    }
}

package com.softserve.edu.hypercinema.exception;

/**
 * Created by VR
 * 18:06, 16.04.2018
 */

public class ServiceException extends RuntimeException {

    public ServiceException(){
    }

    public ServiceException(String message){
        super(message);
    }
}

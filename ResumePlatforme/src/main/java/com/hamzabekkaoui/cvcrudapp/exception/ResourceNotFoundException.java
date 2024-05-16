package com.hamzabekkaoui.cvcrudapp.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }
}

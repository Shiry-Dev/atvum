package com.shirydev.dscatalog.atvum.service.exception;

public class ControllerNotFoundException extends RuntimeException{

    public ControllerNotFoundException(String msg){
        super(msg);
    }
}

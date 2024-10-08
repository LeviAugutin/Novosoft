package com.novosoft.assessment.exception;

public class productNotFoundException  extends RuntimeException{

    public productNotFoundException (String message){
        super(message);
    }
}

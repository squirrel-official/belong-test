package com.test.belong.exception;

public class InvalidPhoneNumberException extends RuntimeException{

    public InvalidPhoneNumberException(String msg){
        super(msg);
    }
}

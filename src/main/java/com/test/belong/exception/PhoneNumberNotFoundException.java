package com.test.belong.exception;

public class PhoneNumberNotFoundException extends RuntimeException{

    public PhoneNumberNotFoundException(String msg){
        super(msg);
    }
}

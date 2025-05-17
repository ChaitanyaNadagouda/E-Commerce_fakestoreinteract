package com.fakestoreInteract.Ecommerce.Exceptions;

public class PasswordDoesntMatchException extends Exception {
    public PasswordDoesntMatchException(String msg){
        super(msg);
    }
}

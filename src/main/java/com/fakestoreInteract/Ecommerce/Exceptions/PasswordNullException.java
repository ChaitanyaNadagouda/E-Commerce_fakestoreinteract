package com.fakestoreInteract.Ecommerce.Exceptions;

public class PasswordNullException extends Exception {
    public PasswordNullException(String passwordCannotBeNull) {
        super(passwordCannotBeNull);
    }
}

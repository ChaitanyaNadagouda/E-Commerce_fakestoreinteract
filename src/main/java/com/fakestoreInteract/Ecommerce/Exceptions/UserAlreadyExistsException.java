package com.fakestoreInteract.Ecommerce.Exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}

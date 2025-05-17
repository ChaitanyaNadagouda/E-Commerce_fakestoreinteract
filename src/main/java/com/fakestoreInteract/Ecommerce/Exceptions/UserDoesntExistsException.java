package com.fakestoreInteract.Ecommerce.Exceptions;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserDoesntExistsException extends Throwable {
    public UserDoesntExistsException(String userDoesntExists) {
    }
}

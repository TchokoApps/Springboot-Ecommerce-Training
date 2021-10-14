package com.tchokoapps.springboot.ecommerce.training.exceptions;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String message) {
        super(message);
    }

}

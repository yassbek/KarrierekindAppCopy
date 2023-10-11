package com.karrierekind.karrierekindapp.utils;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // You can add more constructors or methods if required
}


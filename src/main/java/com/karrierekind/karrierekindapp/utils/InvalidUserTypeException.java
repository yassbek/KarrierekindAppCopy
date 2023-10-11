package com.karrierekind.karrierekindapp.utils;

public class InvalidUserTypeException extends RuntimeException {

    public InvalidUserTypeException(String message) {
        super(message);
    }

    public InvalidUserTypeException(String message, Throwable cause) {
        super(message, cause);
    }

// You can add more constructors or methods if required
}

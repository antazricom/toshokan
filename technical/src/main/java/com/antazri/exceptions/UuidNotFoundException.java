package com.antazri.exceptions;

public class UuidNotFoundException extends Exception {

    public UuidNotFoundException() {
        super();
    }

    public UuidNotFoundException(String message) {
        super(message);
    }

    public UuidNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

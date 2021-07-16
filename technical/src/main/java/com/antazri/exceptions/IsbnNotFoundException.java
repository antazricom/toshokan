package com.antazri.exceptions;

public class IsbnNotFoundException extends Exception {
    public IsbnNotFoundException() {
        super();
    }

    public IsbnNotFoundException(String message) {
        super(message);
    }

    public IsbnNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

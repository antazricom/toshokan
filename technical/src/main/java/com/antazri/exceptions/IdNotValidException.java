package com.antazri.exceptions;

public class IdNotValidException extends Exception {

    public IdNotValidException() {
    }

    public IdNotValidException(String message) {
        super(message);
    }

    public IdNotValidException(String message, Throwable cause) {
        super(message, cause);
    }
}

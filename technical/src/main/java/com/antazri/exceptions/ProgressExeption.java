package com.antazri.exceptions;

public class ProgressExeption extends Exception {

    public ProgressExeption() {
        super();
    }

    public ProgressExeption(String message) {
        super(message);
    }

    public ProgressExeption(String message, Throwable cause) {
        super(message, cause);
    }
}

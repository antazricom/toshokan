package com.antazri.exceptions;

public class NoteException extends Exception {

    public NoteException() {
        super();
    }

    public NoteException(String message) {
        super(message);
    }

    public NoteException(String message, Throwable cause) {
        super(message, cause);
    }
}

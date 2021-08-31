package com.gorany.ichatupush.exception;

public class NoReceiverException extends RuntimeException{

    public NoReceiverException() {
    }

    public NoReceiverException(String message) {
        super(message);
    }
}

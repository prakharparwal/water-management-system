package com.fabric.wms.exception;

public class InvalidNumberOfGuestsException extends RuntimeException {
    private String message;

    public InvalidNumberOfGuestsException(String message) {
        super(message);
        this.message = message;
    }
}

package com.fabric.wms.exception;

public class InvalidApartmentIdException extends RuntimeException {

    private String message;

    public InvalidApartmentIdException(String message) {
        super(message);
        this.message = message;
    }
}

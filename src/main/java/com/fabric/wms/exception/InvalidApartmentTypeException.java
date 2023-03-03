package com.fabric.wms.exception;

public class InvalidApartmentTypeException extends RuntimeException {
    private String message;

    public InvalidApartmentTypeException(String message) {
        super(message);
        this.message = message;
    }
}

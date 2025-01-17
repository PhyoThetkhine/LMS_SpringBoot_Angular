package com.system.Learning_system_springboot.model.exception;
public class InvalidFieldsException extends RuntimeException {
    public InvalidFieldsException(String message) {
        super(message);
    }
}
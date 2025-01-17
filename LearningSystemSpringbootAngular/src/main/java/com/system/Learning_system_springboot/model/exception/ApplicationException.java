package com.system.Learning_system_springboot.model.exception;

public class ApplicationException extends RuntimeException{
    public ApplicationException() {}

    public ApplicationException(String message) {
        super(message);
    }
}

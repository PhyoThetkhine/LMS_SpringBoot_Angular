package com.system.Learning_system_springboot.model.exception;
public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}

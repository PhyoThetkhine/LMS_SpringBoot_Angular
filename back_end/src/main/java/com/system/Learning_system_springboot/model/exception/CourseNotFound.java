package com.system.Learning_system_springboot.model.exception;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound(String message) {
        super(message);
    }
}

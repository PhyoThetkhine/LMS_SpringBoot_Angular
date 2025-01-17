package com.system.Learning_system_springboot.model.entity;

public enum Role {
    ADMIN("Admin"),
    TEACHER("Teacher"),
    DEPARTMENT_HEAD("Department Head"),
    STUDENT("Student");
    private String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

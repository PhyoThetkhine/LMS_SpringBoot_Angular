package com.system.Learning_system_springboot.model.entity;

public enum EnrollPosition {
    STUDENT_ENROLL("Student_Enroll"),
    TEACHER_ENROLL("Teacher_Enroll");
    private String displayName;

    EnrollPosition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

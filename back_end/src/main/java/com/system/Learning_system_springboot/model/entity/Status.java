package com.system.Learning_system_springboot.model.entity;

public enum Status {
    ACTIVE("Active"),
    TERMINATE("Terminate"),
    COMPLETED("Completed"),
    DROP("Drop"),
    DRAFT("Draft"),
    PUBLISHED("Published"),
        SCHEDULED("Scheduled");


    private String displayName;
    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

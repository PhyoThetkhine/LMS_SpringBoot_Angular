package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Assignment;
import com.system.Learning_system_springboot.model.entity.User;

import java.sql.Date;

public class SubmissionDTO {
    private Integer id;
    private Assignment assignment;
    private Integer assignmentID;
    private Integer createStudentID;
    private String createStudentName;
    private User createStudent;
    private Date createDate;
    private Date updateDate;
    private Integer mark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Integer getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(Integer assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getCreateStudentName() {
        return createStudentName;
    }

    public void setCreateStudentName(String createStudentName) {
        this.createStudentName = createStudentName;
    }

    public Integer getCreateStudentID() {
        return createStudentID;
    }

    public void setCreateStudentID(Integer createStudentID) {
        this.createStudentID = createStudentID;
    }

    public User getCreateStudent() {
        return createStudent;
    }

    public void setCreateStudent(User createStudent) {
        this.createStudent = createStudent;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }
}

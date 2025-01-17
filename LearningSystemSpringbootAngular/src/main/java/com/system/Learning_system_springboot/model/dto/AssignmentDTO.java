package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Course;
import com.system.Learning_system_springboot.model.entity.User;

import java.time.LocalDateTime;
import java.util.Date;

public class AssignmentDTO {
    private Integer id;
    private String title;
    private String description;
    private Course course;
    private Integer courseID;
    private LocalDateTime dueDate;
    private String point;
    private Date createDate;
    private Date updateDate;
    private User createTeacher;
    private Integer createTeacherID;
    private String createTeacherName;
    private String status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
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

    public User getCreateTeacher() {
        return createTeacher;
    }

    public void setCreateTeacher(User createTeacher) {
        this.createTeacher = createTeacher;
    }

    public Integer getCreateTeacherID() {
        return createTeacherID;
    }

    public void setCreateTeacherID(Integer createTeacherID) {
        this.createTeacherID = createTeacherID;
    }

    public String getCreateTeacherName() {
        return createTeacherName;
    }

    public void setCreateTeacherName(String createTeacherName) {
        this.createTeacherName = createTeacherName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

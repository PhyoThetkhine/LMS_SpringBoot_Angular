package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Course;
import com.system.Learning_system_springboot.model.entity.EnrollPosition;
import com.system.Learning_system_springboot.model.entity.User;

import java.sql.Date;

public class UserCourseEnrollDTO {
    private Integer courseId;
    private Course course;
    private Integer userId;
    private Integer createAdminId;
    private String createAdminName;
    private User createAdmin;
    private User user;
    private String status;
    private Date createDate;
    private Date updateDate;
    private EnrollPosition enrollPosition;

    public EnrollPosition getEnrollPosition() {
        return enrollPosition;
    }

    public void setEnrollPosition(EnrollPosition enrollPosition) {
        this.enrollPosition = enrollPosition;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCreateAdminId() {
        return createAdminId;
    }

    public void setCreateAdminId(Integer createAdminId) {
        this.createAdminId = createAdminId;
    }

    public String getCreateAdminName() {
        return createAdminName;
    }

    public void setCreateAdminName(String createAdminName) {
        this.createAdminName = createAdminName;
    }

    public User getCreateAdmin() {
        return createAdmin;
    }

    public void setCreateAdmin(User createAdmin) {
        this.createAdmin = createAdmin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}

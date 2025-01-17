package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Course;
import com.system.Learning_system_springboot.model.entity.User;

import java.sql.Date;

public class MaterialDTO {
    private Integer id;
    private String title;
    private String description;
    private Course course;
    private User createTeacher;
    private Date createDate;
    private Date updateDate;
    private Integer courseID;
    private Integer createTeacherID;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getCreateTeacher() {
        return createTeacher;
    }

    public void setCreateTeacher(User createTeacher) {
        this.createTeacher = createTeacher;
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

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    public Integer getCreateTeacherID() {
        return createTeacherID;
    }

    public void setCreateTeacherID(Integer createTeacherID) {
        this.createTeacherID = createTeacherID;
    }
}

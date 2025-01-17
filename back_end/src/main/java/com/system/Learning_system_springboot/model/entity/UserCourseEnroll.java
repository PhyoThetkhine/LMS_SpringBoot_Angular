package com.system.Learning_system_springboot.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
@Entity
@Table(name = "user_course_enroll")
public class UserCourseEnroll {
    @EmbeddedId
    private UserCourseEnroll.UserCourseEnrollPK id;

    @ManyToOne
    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "create_admin_id", nullable = false)
    private User createAdmin;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;
    @Enumerated(EnumType.STRING)
    @Column(name ="enroll_position", nullable = false)
    private EnrollPosition enrollPosition;

    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = new Date(System.currentTimeMillis());
        }
        if (this.updateDate == null) {
            this.updateDate = new Date(System.currentTimeMillis());
        }
        if (this.status == null) {
            this.status = Status.ACTIVE; // Default value
        }
    }

    public UserCourseEnrollPK getId() {
        return id;
    }

    public void setId(UserCourseEnrollPK id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCreateAdmin() {
        return createAdmin;
    }

    public void setCreateAdmin(User createAdmin) {
        this.createAdmin = createAdmin;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public EnrollPosition getEnrollPosition() {
        return enrollPosition;
    }

    public void setEnrollPosition(EnrollPosition enrollPosition) {
        this.enrollPosition = enrollPosition;
    }

    @Embeddable
    public static class UserCourseEnrollPK implements Serializable {
        private Integer courseId;
        private Integer userId;
        public Integer getCourseId() {
            return courseId;
        }
        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }
        public Integer getUserIdId() {
            return userId;
        }
        public void setUserId(Integer teacherId) {
            this.userId = teacherId;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            UserCourseEnrollPK that = (UserCourseEnrollPK) o;
            return Objects.equals(courseId, that.courseId) && Objects.equals(userId, that.userId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(courseId, userId);
        }
    }

}

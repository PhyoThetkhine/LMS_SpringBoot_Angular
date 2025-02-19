package com.system.Learning_system_springboot.model.entity;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "assignment_id", nullable = false)
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "create_student_id", nullable = false)
    private User createStudent;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "update_date", nullable = false)
    private Date updateDate;

    @Column(name = "mark", nullable = false)
    private Integer mark;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @PrePersist
    public void prePersist() {
        if (this.createDate == null) {
            this.createDate = new Date(System.currentTimeMillis());
        }
        if (this.updateDate == null) {
            this.updateDate = new Date(System.currentTimeMillis());
        }
        if(this.mark==null) {
            this.mark=0;
        }
        if (this.status == null) {
            this.status = Status.ACTIVE; // Default value
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public User getCreateStudent() {
        return createStudent;
    }

    public void setCreateStudent(User createStudent) {
        this.createStudent = createStudent;
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

    public Integer getMark() {
        return mark;
    }

    public void setMark(Integer mark) {
        this.mark = mark;
    }


}
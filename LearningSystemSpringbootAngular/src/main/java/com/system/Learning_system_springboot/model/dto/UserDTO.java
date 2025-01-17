package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Role;
import com.system.Learning_system_springboot.model.entity.User;

import java.sql.Date;

public class UserDTO {
    private Integer id;
    private String userCode;
    private String email;
    private String name;
    private String status;
    private String password;
    private String createAdminCode;
    private User createAdmin;
    private Role role;
    private Date createDate;
    private Date updateDate;

    public String getEmail() {
        return email;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateAdminCode() {
        return createAdminCode;
    }

    public void setCreateAdminCode(String createAdminCode) {
        this.createAdminCode = createAdminCode;
    }

    public User getCreateAdmin() {
        return createAdmin;
    }

    public void setCreateAdmin(User createAdmin) {
        this.createAdmin = createAdmin;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

package com.system.Learning_system_springboot.model.dto;

import com.system.Learning_system_springboot.model.entity.Role;
import com.system.Learning_system_springboot.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
}

package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.UserDTO;
import com.system.Learning_system_springboot.model.entity.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface UserService {
    UserDTO saveUser(UserDTO dto);
    String generateUserCode(Role role);
    UserDTO getByCode(String code);
    UserDTO getById(Integer id);
    Page<UserDTO> getAllUser(Pageable pageable);
    void terminateUserById(Integer id);
    void activeUserById(Integer id);
    List<UserDTO> findStudentsNotEnrolledInCourse(Integer courseId);
    List<UserDTO> findTeachersNotAssignedToCourse(Integer courseId);
    void changePassword(String userCode, String currentPassword, String newPassword);
    UserDTO updateByUser(UserDTO dto);
    Page<UserDTO> getStudents(Pageable pageable);
    Page<UserDTO> getTeachers(Pageable pageable);
    List<UserDTO> getStudents();
}
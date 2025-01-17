package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.UserCourseEnrollDTO;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCourseEnrollService {
    Page<UserCourseEnrollDTO> findActiveStudentByCourse(Integer courseId, Pageable pageable);
    List<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId);
    public Page<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId, Pageable pageable);
    Page<UserCourseEnrollDTO> findActiveTeacherByCourse(Integer courseId, Pageable pageable);
    List<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId);
    Page<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId,Pageable pageable);
     UserCourseEnrollDTO getUserCourseById(UserCourseEnroll.UserCourseEnrollPK id) ;
     void saveUserCourseStudent(UserCourseEnrollDTO dto) ;
     void saveUserCourseTeacher(UserCourseEnrollDTO dto) ;
     void deleteUserCourseEnroll(UserCourseEnroll.UserCourseEnrollPK id);
     void reEnrollUserCourse(UserCourseEnroll.UserCourseEnrollPK id);
   Page<UserCourseEnrollDTO> getAll(Pageable pageable) ;
}

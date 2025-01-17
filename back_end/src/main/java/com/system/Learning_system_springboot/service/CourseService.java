package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.CourseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
public interface CourseService {
    void save(CourseDTO course);
    CourseDTO findById(Integer id);
    Page<CourseDTO> findAll(Pageable pageable);
    void update(CourseDTO course);
    void delete(CourseDTO id);
    Page<CourseDTO> findCoursesByTeacherId(Integer teacherId, Pageable pageable);
    Page<CourseDTO> findCoursesByStudentId(Integer studentId, Pageable pageable);
}
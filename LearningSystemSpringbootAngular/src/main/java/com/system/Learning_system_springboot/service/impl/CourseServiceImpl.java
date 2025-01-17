package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.CourseDTO;
import com.system.Learning_system_springboot.model.entity.Course;
import com.system.Learning_system_springboot.model.entity.User;
import com.system.Learning_system_springboot.model.exception.CourseNotFound;
import com.system.Learning_system_springboot.model.repo.CourseRepository;
import com.system.Learning_system_springboot.model.repo.UserRepository;
import com.system.Learning_system_springboot.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(CourseDTO courseDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Course course = modelMapper.map(courseDTO, Course.class);
        User createAdmin = userRepository.findById(courseDTO.getCreateAdminId())
                .orElseThrow(() -> new RuntimeException("Create admin not found"));
        course.setCreateAdmin(createAdmin);
        courseRepository.save(course);
    }

    @Override
    public CourseDTO findById(Integer id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFound("Course not found with id: " + id));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(course, CourseDTO.class);
    }

    @Override
    public Page<CourseDTO> findAll(Pageable pageable) {
        Page<Course> courses = courseRepository.findAll(pageable);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return courses.map(course -> modelMapper.map(course, CourseDTO.class));
    }

    @Override
    public void update(CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseDTO.getId())
                .orElseThrow(() -> new CourseNotFound("Course not found with id: " + courseDTO.getId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(courseDTO, existingCourse);
        if (courseDTO.getCreateAdminId() != null) {
            User createAdmin = userRepository.findById(courseDTO.getCreateAdminId())
                    .orElseThrow(() -> new RuntimeException("Create admin not found"));
            existingCourse.setCreateAdmin(createAdmin);
        }
        courseRepository.save(existingCourse);
    }

    @Override
    public void delete(CourseDTO courseDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Course course = modelMapper.map(courseDTO, Course.class);
        courseRepository.delete(course);
    }

    @Override
    public Page<CourseDTO> findCoursesByTeacherId(Integer teacherId, Pageable pageable) {
        Page<Course> courses = courseRepository.findCoursesByTeacherId(teacherId, pageable);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return courses.map(course -> modelMapper.map(course, CourseDTO.class));
    }

    @Override
    public Page<CourseDTO> findCoursesByStudentId(Integer studentId, Pageable pageable) {
        Page<Course> courses = courseRepository.findCoursesByStudentId(studentId, pageable);
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return courses.map(course -> modelMapper.map(course, CourseDTO.class));
    }
}

package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.UserCourseEnrollDTO;
import com.system.Learning_system_springboot.model.entity.EnrollPosition;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll.UserCourseEnrollPK;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.UserCourseEnrollRepository;
import com.system.Learning_system_springboot.service.UserCourseEnrollService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserCourseEnrollServiceImpl implements UserCourseEnrollService {

    @Autowired
    private UserCourseEnrollRepository userCourseEnrollRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<UserCourseEnrollDTO> findActiveStudentByCourse(Integer courseId, Pageable pageable) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.STUDENT_ENROLL, Status.ACTIVE, pageable
        ).map(this::convertToDTO);
    }

    @Override
    public List<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                        courseId, EnrollPosition.STUDENT_ENROLL, Status.DROP
                ).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserCourseEnrollDTO> findDropStudentByCourse(Integer courseId, Pageable pageable) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.STUDENT_ENROLL, Status.DROP, pageable
        ).map(this::convertToDTO);
    }

    @Override
    public Page<UserCourseEnrollDTO> findActiveTeacherByCourse(Integer courseId, Pageable pageable) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.TEACHER_ENROLL, Status.ACTIVE, pageable
        ).map(this::convertToDTO);
    }

    @Override
    public List<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                        courseId, EnrollPosition.TEACHER_ENROLL, Status.DROP
                ).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<UserCourseEnrollDTO> findDropTeacherByCourse(Integer courseId, Pageable pageable) {
        return userCourseEnrollRepository.findByCourseIdAndEnrollPositionAndStatus(
                courseId, EnrollPosition.TEACHER_ENROLL, Status.DROP, pageable
        ).map(this::convertToDTO);
    }

    @Override
    public UserCourseEnrollDTO getUserCourseById(UserCourseEnrollPK id) throws ServiceException {
        UserCourseEnroll userCourseEnroll = userCourseEnrollRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UserCourseEnroll not found with id: " + id));
        return convertToDTO(userCourseEnroll);
    }

    @Override
    public void saveUserCourseStudent(UserCourseEnrollDTO dto) {
        UserCourseEnroll userCourseEnroll = convertToEntity(dto);
        userCourseEnroll.setEnrollPosition(EnrollPosition.STUDENT_ENROLL);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public void saveUserCourseTeacher(UserCourseEnrollDTO dto) {
        UserCourseEnroll userCourseEnroll = convertToEntity(dto);
        userCourseEnroll.setEnrollPosition(EnrollPosition.TEACHER_ENROLL);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public void deleteUserCourseEnroll(UserCourseEnrollPK id) throws ServiceException {
        if (!userCourseEnrollRepository.existsById(id)) {
            throw new ServiceException("UserCourseEnroll not found with id: " + id);
        }
        userCourseEnrollRepository.deleteById(id);
    }

    @Override
    public void reEnrollUserCourse(UserCourseEnrollPK id) throws ServiceException {
        UserCourseEnroll userCourseEnroll = userCourseEnrollRepository.findById(id)
                .orElseThrow(() -> new ServiceException("UserCourseEnroll not found with id: " + id));
        userCourseEnroll.setStatus(Status.ACTIVE);
        userCourseEnrollRepository.save(userCourseEnroll);
    }

    @Override
    public Page<UserCourseEnrollDTO> getAll(Pageable pageable) {
        return userCourseEnrollRepository.findAll(pageable)
                .map(this::convertToDTO);
    }

    // Helper method to convert Entity to DTO using ModelMapper
    private UserCourseEnrollDTO convertToDTO(UserCourseEnroll userCourseEnroll) {
        return modelMapper.map(userCourseEnroll, UserCourseEnrollDTO.class);
    }

    // Helper method to convert DTO to Entity using ModelMapper
    private UserCourseEnroll convertToEntity(UserCourseEnrollDTO dto) {
        return modelMapper.map(dto, UserCourseEnroll.class);
    }
}

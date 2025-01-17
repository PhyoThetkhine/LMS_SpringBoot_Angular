package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.AssignmentDTO;

import java.util.List;

public interface AssignmentService {
     void save(AssignmentDTO dto) ;
     void update(AssignmentDTO dto) ;
     AssignmentDTO findById(Integer id) ;
     List<AssignmentDTO> findAll();
     void delete(Integer id) ;
     List<AssignmentDTO> findAssignmentsByCourseId(Integer courseId) ;
}

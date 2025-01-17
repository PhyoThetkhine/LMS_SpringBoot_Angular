package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.SubmissionDTO;

import java.util.List;

public interface SubmissionService {
     void save(SubmissionDTO dto);
     void update(SubmissionDTO dto);
     SubmissionDTO findById(Integer id);
     List<SubmissionDTO> findAll();
     void delete(Integer id);
     List<SubmissionDTO> findSubmissionsByAssignmentId(Integer assignmentId);
    List<SubmissionDTO> getSubmissionByAssignmentIdAndStudentId(Integer assignmentId,Integer studentId);
}

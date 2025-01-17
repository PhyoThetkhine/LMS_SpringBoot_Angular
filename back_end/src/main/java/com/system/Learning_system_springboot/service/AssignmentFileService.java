package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.AssignmentFileDTO;

import java.util.List;

public interface AssignmentFileService {
     void saveAssignmentFile(AssignmentFileDTO dto);
     List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId);
     void addNewFile(AssignmentFileDTO dto) ;

}

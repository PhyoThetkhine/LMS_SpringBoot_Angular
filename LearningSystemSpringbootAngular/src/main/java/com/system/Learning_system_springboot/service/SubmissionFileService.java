package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.SubmissionDTO;
import com.system.Learning_system_springboot.model.dto.SubmissionFileDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubmissionFileService {
     void saveSubmissionFile(SubmissionFileDTO dto);
    List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId);
     SubmissionFileDTO getFileById(Integer Id);
     void saveNewFile(SubmissionFileDTO submissionFileDTO);
}

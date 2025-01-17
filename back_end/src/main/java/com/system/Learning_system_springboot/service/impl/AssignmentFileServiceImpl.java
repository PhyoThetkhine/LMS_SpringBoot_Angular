package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.AssignmentFileDTO;
import com.system.Learning_system_springboot.model.entity.Assignment;
import com.system.Learning_system_springboot.model.entity.AssignmentFile;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.AssignmentFileRepository;
import com.system.Learning_system_springboot.model.repo.AssignmentRepository;
import com.system.Learning_system_springboot.service.AssignmentFileService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentFileServiceImpl implements AssignmentFileService {
    private final AssignmentFileRepository assignmentFileRepository;
    private final AssignmentRepository assignmentRepository;
    private final ModelMapper modelMapper;

    public AssignmentFileServiceImpl(AssignmentFileRepository assignmentFileRepository, AssignmentRepository assignmentRepository, ModelMapper modelMapper) {
        this.assignmentFileRepository = assignmentFileRepository;
        this.assignmentRepository = assignmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveAssignmentFile(AssignmentFileDTO assignmentFileDTO) {
        // Fetch the assignment entity
        Assignment assignment = assignmentRepository.findById(assignmentFileDTO.getAssignmentID())
                .orElseThrow(() -> new ServiceException("Assignment not found with id: " + assignmentFileDTO.getAssignmentID()));

        // Map DTO to entity
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        AssignmentFile assignmentFile = modelMapper.map(assignmentFileDTO, AssignmentFile.class);
        assignmentFile.setAssignment(assignment);

        // Save the entity
        assignmentFileRepository.save(assignmentFile);
    }

    @Override
    public List<AssignmentFileDTO> getFilesByAssignmentId(Integer assignmentId) {
        List<AssignmentFile> assignmentFiles = assignmentFileRepository.findByAssignmentId(assignmentId);
        return assignmentFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void addNewFile(AssignmentFileDTO dto) {
        // Fetch the assignment entity
        Assignment assignment = assignmentRepository.findById(dto.getAssignmentID())
                .orElseThrow(() -> new ServiceException("Assignment not found with id: " + dto.getAssignmentID()));

        // Map DTO to entity
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        AssignmentFile assignmentFile = modelMapper.map(dto, AssignmentFile.class);
        assignmentFile.setAssignment(assignment);

        // Save the entity
        assignmentFileRepository.save(assignmentFile);
    }

    // Helper method to convert entity to DTO
    private AssignmentFileDTO convertToDTO(AssignmentFile assignmentFile) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        AssignmentFileDTO dto = modelMapper.map(assignmentFile, AssignmentFileDTO.class);
        dto.setAssignmentID(assignmentFile.getAssignment().getId());
        return dto;
    }
}
package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.AssignmentDTO;
import com.system.Learning_system_springboot.model.entity.Assignment;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.AssignmentRepository;
import com.system.Learning_system_springboot.service.AssignmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssignmentServiceImpl implements AssignmentService {
    private final AssignmentRepository assignmentRepository;
    private final ModelMapper modelMapper;
    public AssignmentServiceImpl(AssignmentRepository assignmentRepository, ModelMapper modelMapper) {
        this.assignmentRepository = assignmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(AssignmentDTO assignmentDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Assignment assignment = modelMapper.map(assignmentDTO, Assignment.class);
        assignmentRepository.save(assignment);
    }

    @Override
    public void update(AssignmentDTO assignmentDTO) {
        Assignment existingAssignment = assignmentRepository.findById(assignmentDTO.getId())
                .orElseThrow(() -> new ServiceException("Assignment not found with id: " + assignmentDTO.getId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        // Map updated fields from DTO to the existing assignment
        modelMapper.map(assignmentDTO, existingAssignment);

        // Save the updated assignment
        assignmentRepository.save(existingAssignment);
    }

    @Override
    public AssignmentDTO findById(Integer id) {
        Assignment assignment = assignmentRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new ServiceException("Assignment not found with id: " + id));
        return convertToDTO(assignment);
    }

    @Override
    public List<AssignmentDTO> findAll() {
        List<Assignment> assignments = assignmentRepository.findByStatus(Status.ACTIVE);
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        Assignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Assignment not found with id: " + id));
        assignment.setStatus(Assignment.Status.DROP); // Soft delete by setting status to DROP
        assignmentRepository.save(assignment);
    }

    @Override
    public List<AssignmentDTO> findAssignmentsByCourseId(Integer courseId) {
        List<Assignment> assignments = assignmentRepository.findByCourseIdAndStatus(courseId, Status.ACTIVE);
        return assignments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Helper method to convert entity to DTO
    private AssignmentDTO convertToDTO(Assignment assignment) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(assignment, AssignmentDTO.class);
    }
}
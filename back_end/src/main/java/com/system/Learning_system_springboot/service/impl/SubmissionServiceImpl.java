package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.SubmissionDTO;
import com.system.Learning_system_springboot.model.entity.Submission;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.SubmissionRepository;
import com.system.Learning_system_springboot.service.SubmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final ModelMapper modelMapper;

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, ModelMapper modelMapper) {
        this.submissionRepository = submissionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void save(SubmissionDTO submissionDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Submission submission = modelMapper.map(submissionDTO, Submission.class);
        submission.setStatus(Status.ACTIVE);
        submissionRepository.save(submission);
    }

    @Override
    public void update(SubmissionDTO submissionDTO) {
        Submission existingSubmission = submissionRepository.findById(submissionDTO.getId())
                .orElseThrow(() -> new ServiceException("Submission not found with id: " + submissionDTO.getId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(submissionDTO, existingSubmission);
        submissionRepository.save(existingSubmission);
    }

    @Override
    public SubmissionDTO findById(Integer id) {
        Submission submission = submissionRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new ServiceException("Submission not found with id: " + id));
        return convertToDTO(submission);
    }

    @Override
    public List<SubmissionDTO> findAll() {
        List<Submission> submissions = submissionRepository.findByStatus(Status.ACTIVE);
        return submissions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public void delete(Integer id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Submission not found with id: " + id));
        submission.setStatus(Status.DROP);
        submissionRepository.save(submission);
    }

    @Override
    public List<SubmissionDTO> findSubmissionsByAssignmentId(Integer assignmentId) {
        List<Submission> submissions = submissionRepository.findByAssignmentIdAndStatus(assignmentId, Status.ACTIVE);
        return submissions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<SubmissionDTO> getSubmissionByAssignmentIdAndStudentId(Integer assignmentId, Integer studentId) {
        List<Submission> submissions = submissionRepository.findByAssignmentIdAndCreateStudentIdAndStatus(assignmentId, studentId, Status.ACTIVE);
        return submissions.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private SubmissionDTO convertToDTO(Submission submission) {
        return modelMapper.map(submission, SubmissionDTO.class);
    }
}

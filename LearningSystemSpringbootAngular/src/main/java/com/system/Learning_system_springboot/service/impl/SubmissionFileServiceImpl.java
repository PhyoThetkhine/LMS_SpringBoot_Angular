package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.SubmissionDTO;
import com.system.Learning_system_springboot.model.dto.SubmissionFileDTO;
import com.system.Learning_system_springboot.model.entity.Submission;
import com.system.Learning_system_springboot.model.entity.SubmissionFile;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.SubmissionFileRepository;
import com.system.Learning_system_springboot.model.repo.SubmissionRepository;
import com.system.Learning_system_springboot.service.SubmissionFileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubmissionFileServiceImpl implements SubmissionFileService {
    private final SubmissionFileRepository submissionFileRepository;
    private final SubmissionRepository submissionRepository;
    private final ModelMapper modelMapper;
    public SubmissionFileServiceImpl(SubmissionFileRepository submissionFileRepository, SubmissionRepository submissionRepository, ModelMapper modelMapper) {
        this.submissionFileRepository = submissionFileRepository;
        this.submissionRepository = submissionRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void saveSubmissionFile(SubmissionFileDTO submissionFileDTO) {
        Submission submission = submissionRepository.findById(submissionFileDTO.getSubmissionId())
                .orElseThrow(() -> new ServiceException("Submission not found with id: " + submissionFileDTO.getSubmissionId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        SubmissionFile submissionFile = modelMapper.map(submissionFileDTO, SubmissionFile.class);
        submissionFile.setSubmission(submission);
        submissionFileRepository.save(submissionFile);
    }
    @Override
    public List<SubmissionFileDTO> getFilesBySubmissionId(Integer submissionId) {
        List<SubmissionFile> submissionFiles = submissionFileRepository.findBySubmissionId(submissionId);
        return submissionFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubmissionFileDTO getFileById(Integer id) {
        SubmissionFile submissionFile = submissionFileRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Submission file not found with id: " + id));
        return convertToDTO(submissionFile);
    }
    @Override
    public void saveNewFile(SubmissionFileDTO dto) {
        Submission submission = submissionRepository.findById(dto.getSubmissionId())
                .orElseThrow(() -> new ServiceException("Submission not found with id: " + dto.getSubmissionId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        SubmissionFile submissionFile = modelMapper.map(dto, SubmissionFile.class);
        submissionFile.setSubmission(submission);
        submissionFileRepository.save(submissionFile);
    }

    private SubmissionFileDTO convertToDTO(SubmissionFile submissionFile) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        SubmissionFileDTO dto = modelMapper.map(submissionFile, SubmissionFileDTO.class);
        dto.setSubmissionId(submissionFile.getSubmission().getId());
        return dto;
    }
}
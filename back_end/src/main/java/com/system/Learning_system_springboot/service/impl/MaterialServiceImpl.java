package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.MaterialDTO;
import com.system.Learning_system_springboot.model.entity.Material;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.MaterialRepository;
import com.system.Learning_system_springboot.service.MaterialService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;
    public MaterialServiceImpl(MaterialRepository materialRepository, ModelMapper modelMapper) {
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void save(MaterialDTO materialDTO) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        Material material = modelMapper.map(materialDTO, Material.class);
        materialRepository.save(material);
    }
    @Override
    public void update(MaterialDTO materialDTO) {
        Material existingMaterial = materialRepository.findById(materialDTO.getId())
                .orElseThrow(() -> new ServiceException("Material not found with id: " + materialDTO.getId()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(materialDTO, existingMaterial);
        materialRepository.save(existingMaterial);
    }
    @Override
    public MaterialDTO findById(Integer id) {
        Material material = materialRepository.findByIdAndStatus(id, Status.ACTIVE)
                .orElseThrow(() -> new ServiceException("Material not found with id: " + id));
        return convertToDTO(material);
    }
    @Override
    public List<MaterialDTO> findAll() {
        List<Material> materials = materialRepository.findByStatus(Status.ACTIVE);
        return materials.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void delete(Integer id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Material not found with id: " + id));
        material.setStatus(Status.DROP); // Soft delete by setting status to DROP
        materialRepository.save(material);
    }
    @Override
    public List<MaterialDTO> findMaterialsByCourseId(Integer courseId) {
        List<Material> materials = materialRepository.findByCourseIdAndStatus(courseId, Status.ACTIVE);
        return materials.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    private MaterialDTO convertToDTO(Material material) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper.map(material, MaterialDTO.class);
    }
}
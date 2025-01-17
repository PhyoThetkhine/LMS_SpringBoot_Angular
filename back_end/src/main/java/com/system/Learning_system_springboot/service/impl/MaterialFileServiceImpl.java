package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.dto.MaterialFileDTO;
import com.system.Learning_system_springboot.model.entity.Material;
import com.system.Learning_system_springboot.model.entity.MaterialFile;
import com.system.Learning_system_springboot.model.exception.ServiceException;
import com.system.Learning_system_springboot.model.repo.MaterialFileRepository;
import com.system.Learning_system_springboot.model.repo.MaterialRepository;
import com.system.Learning_system_springboot.service.MaterialFileService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MaterialFileServiceImpl implements MaterialFileService {
    private final MaterialFileRepository materialFileRepository;
    private final MaterialRepository materialRepository;
    private final ModelMapper modelMapper;
    public MaterialFileServiceImpl(MaterialFileRepository materialFileRepository, MaterialRepository materialRepository, ModelMapper modelMapper) {
        this.materialFileRepository = materialFileRepository;
        this.materialRepository = materialRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public void saveMaterialFile(MaterialFileDTO materialFileDTO) {
        Material material = materialRepository.findById(materialFileDTO.getMaterialID())
                .orElseThrow(() -> new ServiceException("Material not found with id: " + materialFileDTO.getMaterialID()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        MaterialFile materialFile = modelMapper.map(materialFileDTO, MaterialFile.class);
        materialFile.setMaterial(material);
        materialFileRepository.save(materialFile);
    }
    @Override
    public void updateMaterialFile(MaterialFileDTO materialFileDTO) {
        MaterialFile existingMaterialFile = materialFileRepository.findById(materialFileDTO.getId())
                .orElseThrow(() -> new ServiceException("Material file not found with id: " + materialFileDTO.getId()));
        if (materialFileDTO.getMaterialID() != null) {
            Material material = materialRepository.findById(materialFileDTO.getMaterialID())
                    .orElseThrow(() -> new ServiceException("Material not found with id: " + materialFileDTO.getMaterialID()));
            existingMaterialFile.setMaterial(material);
        }
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.map(materialFileDTO, existingMaterialFile);
        materialFileRepository.save(existingMaterialFile);
    }
    @Override
    public MaterialFileDTO getMaterialFileById(Integer id) {
        MaterialFile materialFile = materialFileRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Material file not found with id: " + id));
        return convertToDTO(materialFile);
    }
    @Override
    public List<MaterialFileDTO> getAllMaterialFiles() {
        List<MaterialFile> materialFiles = materialFileRepository.findAll();
        return materialFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteMaterialFile(Integer id) {
        MaterialFile materialFile = materialFileRepository.findById(id)
                .orElseThrow(() -> new ServiceException("Material file not found with id: " + id));
        materialFileRepository.delete(materialFile);
    }
    @Override
    public List<MaterialFileDTO> getFilesByMaterialId(Integer materialId) {
        List<MaterialFile> materialFiles = materialFileRepository.findByMaterialId(materialId);
        return materialFiles.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public void addNewFile(MaterialFileDTO dto) {
        Material material = materialRepository.findById(dto.getMaterialID())
                .orElseThrow(() -> new ServiceException("Material not found with id: " + dto.getMaterialID()));
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        MaterialFile materialFile = modelMapper.map(dto, MaterialFile.class);
        materialFile.setMaterial(material);
        materialFileRepository.save(materialFile);
    }
    private MaterialFileDTO convertToDTO(MaterialFile materialFile) {
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        MaterialFileDTO dto = modelMapper.map(materialFile, MaterialFileDTO.class);
        dto.setMaterialID(materialFile.getMaterial().getId());
        return dto;
    }
}
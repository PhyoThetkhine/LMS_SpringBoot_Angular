package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.MaterialFileDTO;

import java.util.List;

public interface MaterialFileService {
     void saveMaterialFile(MaterialFileDTO materialFileDTO);
     void updateMaterialFile(MaterialFileDTO materialFileDTO);
     MaterialFileDTO getMaterialFileById(Integer id);
     List<MaterialFileDTO> getAllMaterialFiles();
     void deleteMaterialFile(Integer id);
     List<MaterialFileDTO> getFilesByMaterialId(Integer materialId);
     void addNewFile(MaterialFileDTO dto);
}

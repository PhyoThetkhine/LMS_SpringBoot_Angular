package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.dto.MaterialDTO;

import java.util.List;

public interface MaterialService {
     void save(MaterialDTO material);
      void update(MaterialDTO material);
      MaterialDTO findById(Integer id);
     List<MaterialDTO> findAll();
     void delete(Integer id);
     List<MaterialDTO> findMaterialsByCourseId(Integer courseId);
}

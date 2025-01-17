package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.MaterialFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialFileRepository extends JpaRepository<MaterialFile,Integer> {
    List<MaterialFile> findByMaterialId(Integer materialId);
}

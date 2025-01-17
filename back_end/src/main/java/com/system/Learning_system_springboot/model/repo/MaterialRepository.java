package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.Material;
import com.system.Learning_system_springboot.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
    List<Material> findByCourseId(Integer courseId);
    List<Material> findByStatus(Status status);
    List<Material> findByCourseIdAndStatus(Integer courseId, Status status);
    Optional<Material> findByIdAndStatus(Integer id, Status status);
}

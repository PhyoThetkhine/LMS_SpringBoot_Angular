package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.Assignment;
import com.system.Learning_system_springboot.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer> {
    List<Assignment> findByStatus(Status status);
    List<Assignment> findByCourseIdAndStatus(Integer courseId, Status status);
    Optional<Assignment> findByIdAndStatus(Integer id, Status status);
}

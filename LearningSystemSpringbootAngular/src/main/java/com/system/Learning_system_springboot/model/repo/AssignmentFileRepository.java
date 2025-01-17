package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.AssignmentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentFileRepository extends JpaRepository<AssignmentFile,Integer> {
    List<AssignmentFile> findByAssignmentId(Integer assignmentId);
}

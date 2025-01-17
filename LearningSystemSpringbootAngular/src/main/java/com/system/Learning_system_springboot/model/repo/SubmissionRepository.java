package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission,Integer> {
    List<Submission> findByStatus(Status status);
    List<Submission> findByAssignmentIdAndStatus(Integer assignmentId, Status status);
    List<Submission> findByAssignmentIdAndCreateStudentIdAndStatus(Integer assignmentId, Integer studentId, Status status);
    Optional<Submission> findByIdAndStatus(Integer id, Status status);
}

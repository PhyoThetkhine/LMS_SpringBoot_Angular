package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.SubmissionFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SubmissionFileRepository extends JpaRepository<SubmissionFile , Integer> {
    List<SubmissionFile> findBySubmissionId(Integer submissionId);
}

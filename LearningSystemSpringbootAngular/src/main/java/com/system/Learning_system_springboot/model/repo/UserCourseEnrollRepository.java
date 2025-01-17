package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.EnrollPosition;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.UserCourseEnroll;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserCourseEnrollRepository extends JpaRepository<UserCourseEnroll, UserCourseEnroll.UserCourseEnrollPK> {
    List<UserCourseEnroll> findByCourseIdAndEnrollPositionAndStatus(
            Integer courseId, EnrollPosition enrollPosition, Status status
    );
    Page<UserCourseEnroll> findByCourseIdAndEnrollPositionAndStatus(
            Integer courseId, EnrollPosition enrollPosition, Status status,Pageable pageable
    );
}

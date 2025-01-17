package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    Page<Course> findByCreateAdminId(Integer createAdminId, Pageable pageable);

    @Query("SELECT uce.course FROM UserCourseEnroll uce WHERE uce.user.id = :studentId")
    Page<Course> findCoursesByStudentId(@Param("studentId") Integer studentId, Pageable pageable);

    @Query("SELECT uce.course FROM UserCourseEnroll uce WHERE uce.user.id = :teacherId")
    Page<Course> findCoursesByTeacherId(@Param("teacherId") Integer teacherId, Pageable pageable);
}


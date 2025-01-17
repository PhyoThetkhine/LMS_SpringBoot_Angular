package com.system.Learning_system_springboot.model.repo;

import com.system.Learning_system_springboot.model.entity.Role;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Long countByRole(Role role);
    List<User> findByRoleAndStatus(Role role, Status status);
    List<User> findByStatus(Status status);
    User findByEmail(String email);
    Optional<User> findByIdAndStatus(Integer id, Status status);
    User findByEmailAndStatus(String email,Status status);
    Optional<User> findByUserCode(String code);
    Page<User> findByRole(Pageable pageable,Role role);

    @Query("SELECT u FROM User u " +
            "WHERE u.role = 'STUDENT' " +
            "AND u.status = 'ACTIVE' " +
            "AND u.id NOT IN (" +
            "   SELECT chs.user.id FROM UserCourseEnroll chs WHERE chs.course.id = :courseId" +
            ")")
    List<User> findStudentsNotEnrolledInCourse(Integer courseId);

    @Query("SELECT u FROM User u " +
            "WHERE u.role = 'TEACHER' " +
            "AND u.status = 'ACTIVE' " +
            "AND u.id NOT IN (" +
            "   SELECT cht.user.id FROM UserCourseEnroll cht WHERE cht.course.id = :courseId" +
            ")")
    List<User> findTeachersNotAssignedToCourse(@Param("courseId") Integer courseId);
}
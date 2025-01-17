package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.CourseAnnouncement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CourseAnnouncementRepository extends JpaRepository<CourseAnnouncement,Integer> {
}

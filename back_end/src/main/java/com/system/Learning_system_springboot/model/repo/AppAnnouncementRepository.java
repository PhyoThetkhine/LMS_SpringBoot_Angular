package com.system.Learning_system_springboot.model.repo;
import com.system.Learning_system_springboot.model.entity.AppAnnouncement;
import com.system.Learning_system_springboot.model.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppAnnouncementRepository extends JpaRepository<AppAnnouncement,Integer> {
    List<AppAnnouncement> findByStatusAndScheduledAtBefore(Status status, LocalDateTime dateTime);
}


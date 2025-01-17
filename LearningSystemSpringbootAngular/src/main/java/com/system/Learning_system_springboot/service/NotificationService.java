package com.system.Learning_system_springboot.service;

import com.system.Learning_system_springboot.model.entity.AppAnnouncement;

public interface NotificationService {
    void checkScheduledAnnouncements();
    void sendNotification(AppAnnouncement announcement);
}

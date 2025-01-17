package com.system.Learning_system_springboot.service.impl;

import com.system.Learning_system_springboot.model.entity.AppAnnouncement;
import com.system.Learning_system_springboot.model.entity.Status;
import com.system.Learning_system_springboot.model.repo.AppAnnouncementRepository;
import com.system.Learning_system_springboot.service.NotificationService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    private final AppAnnouncementRepository appAnnouncementRepository;
    private final SimpMessagingTemplate messagingTemplate;
    private final SimpUserRegistry simpUserRegistry;

    public NotificationServiceImpl(AppAnnouncementRepository appAnnouncementRepository,
                                   SimpMessagingTemplate messagingTemplate,
                                   SimpUserRegistry simpUserRegistry) {
        this.appAnnouncementRepository = appAnnouncementRepository;
        this.messagingTemplate = messagingTemplate;
        this.simpUserRegistry = simpUserRegistry;
    }

    @Override
    @Scheduled(fixedRate = 60000)
    public void checkScheduledAnnouncements() {
        List<AppAnnouncement> announcements = appAnnouncementRepository.findByStatusAndScheduledAtBefore(Status.SCHEDULED, LocalDateTime.now());
        for (AppAnnouncement announcement : announcements) {
            sendNotification(announcement);
            announcement.setStatus(Status.PUBLISHED);
            appAnnouncementRepository.save(announcement);
        }
    }
    @Override
    public void sendNotification(AppAnnouncement announcement) {
        String message = String.format("Announcement\n%s", announcement.getContent());
        System.out.println("Content: " + announcement.getContent() + " at " + LocalDateTime.now());

        // Count subscribers
        int subscriberCount = countSubscribers("/topic/notifications");
        System.out.println("Number of subscribers to /topic/notifications: " + subscriberCount);

        // Send the notification
        messagingTemplate.convertAndSend("/topic/notifications", message);
    }

    private int countSubscribers(String topic) {
        simpUserRegistry.getUsers().forEach(user -> {
            System.out.println("User: " + user.getName());
            user.getSessions().forEach(session -> {
                System.out.println("Session: " + session.getId());
                session.getSubscriptions().forEach(subscription -> {
                    System.out.println("Subscription: " + subscription.getId() + " -> " + subscription.getDestination());
                });
            });
        });

        return simpUserRegistry.getUsers().stream()
                .flatMap(user -> user.getSessions().stream())
                .filter(session -> session.getSubscriptions().stream()
                        .anyMatch(subscription -> subscription.getDestination().equals(topic)))
                .toList()
                .size();
    }
}
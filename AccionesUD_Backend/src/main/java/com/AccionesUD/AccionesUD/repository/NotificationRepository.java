package com.AccionesUD.AccionesUD.repository;


// repository/NotificationRepository.java
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.AccionesUD.AccionesUD.domain.model.notification.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByRecipient(String recipient);
    List<Notification> findByRecipientAndType(String recipient, String type);
    List<Notification> findByRecipientAndCreatedAtAfter(String recipient, LocalDateTime date);
    List<Notification> findByRecipientAndMessageContainingIgnoreCase(String recipient, String message);
}

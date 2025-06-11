package com.AccionesUD.AccionesUD.domain.service.notification;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.AccionesUD.AccionesUD.domain.model.notification.Notification;
import com.AccionesUD.AccionesUD.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository repository;

    public NotificationService(NotificationRepository repository) {
        this.repository = repository;
    }

    public Notification save(Notification notification) {
        notification.setCreatedAt(java.time.LocalDateTime.now());
        return repository.save(notification);
    }

    // domain/service/notification/NotificationService.java
public List<Notification> getByRecipient(String username) {
    return repository.findByRecipient(username);
}

public List<Notification> getByRecipientAndType(String username, String type) {
    return repository.findByRecipientAndType(username, type);
}

public List<Notification> getByRecipientAndDateAfter(String username, LocalDateTime date) {
    return repository.findByRecipientAndCreatedAtAfter(username, date);
}

public List<Notification> getByRecipientAndKeyword(String username, String keyword) {
    return repository.findByRecipientAndMessageContainingIgnoreCase(username, keyword);
}

}
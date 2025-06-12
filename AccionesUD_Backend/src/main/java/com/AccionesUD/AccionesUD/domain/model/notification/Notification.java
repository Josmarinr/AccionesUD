package com.AccionesUD.AccionesUD.domain.model.notification;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Data
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String recipient;

    private String type;

    @Column(nullable = false)
    private String title;

    private String message;
    
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;


    @Column(nullable = false)
    private boolean read = false; 


    public Notification() {
        this.createdAt = LocalDateTime.now();
    }

    @EnableJpaAuditing
    @Configuration
    public class JpaConfig {}
}

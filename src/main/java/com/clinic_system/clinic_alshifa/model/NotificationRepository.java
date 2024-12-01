package com.clinic_system.clinic_alshifa.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Find all unread notifications
    List<Notification> findByIsReadFalse();
    int countByIsReadFalse(); // Counts notifications where isRead is false
}
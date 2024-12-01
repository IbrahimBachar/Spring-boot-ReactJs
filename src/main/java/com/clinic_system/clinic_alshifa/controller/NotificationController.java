package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.Notification;
import com.clinic_system.clinic_alshifa.model.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Display unread notifications in Thymeleaf view
    @GetMapping("/notifications")
    public String getUnreadNotifications(Model model) {
        List<Notification> notifications = notificationService.getUnreadNotifications();
        model.addAttribute("notifications", notifications);
        return "notifications"; // Thymeleaf template "notifications.html"
    }

    // Endpoint for fetching the unread notification count
    @GetMapping("/notifications/count")
    @ResponseBody
    public Map<String, Integer> getUnreadNotificationCount() {
        int unreadCount = notificationService.getUnreadNotificationCount();
        return Collections.singletonMap("count", unreadCount);
    }

    // Mark a notification as read and redirect to notifications page
    @PostMapping("/notifications/{id}/mark-as-read")
    public String markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return "redirect:/admin/notifications"; // Redirect to refresh notifications
    }
}
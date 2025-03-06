package com.example.auth_service.services;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class TokenExpirationSchedulerService {

    @Autowired
    private TaskScheduler scheduler;
    private TokenExpirationNotificationService tokenExpirationNotificationService;
    private final Map<String, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public TokenExpirationSchedulerService(TaskScheduler scheduler,
            TokenExpirationNotificationService tokenExpirationNotificationService) {
        this.scheduler = scheduler;
        this.tokenExpirationNotificationService = tokenExpirationNotificationService;
    }

    public void scheduleTokenExpirationNotification(String userUID, Instant expirationTime) {
        long delay = Duration.between(Instant.now(), expirationTime).toMillis();

        ScheduledFuture<?> scheduledFuture = scheduler.schedule(() -> {
            tokenExpirationNotificationService.notifyListeners(userUID);
            scheduledTasks.remove(userUID);

        }, Instant.now().plusMillis(delay));

        scheduledTasks.put(userUID, scheduledFuture);

    }

    public void cancelTokenExpirationNotification(String userUID) {
        ScheduledFuture<?> scheduledFuture = scheduledTasks.remove(userUID);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

}

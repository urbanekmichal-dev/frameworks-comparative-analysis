package com.example.notification.service;

import com.example.notification.dto.NotificationDtoResponse;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Singleton;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Singleton
public class NotificationService {

    private static final String CURRENT_DATETIME = "Current datetime: ";

    private final NotificationConverter notificationConverter;

    public NotificationService(NotificationConverter notificationConverter) {
        this.notificationConverter = notificationConverter;
    }

    private String getCurrentDateTimeMessage(){
        String localDateTime = String.valueOf(java.time.LocalDateTime.now());
        return CURRENT_DATETIME + localDateTime;
    }

    public Mono<NotificationDtoResponse> getDateTime(){
        return Mono.just(getCurrentDateTimeMessage())
                .map(notificationConverter::notificationStringToNotificationDtoResponse);
    }

    public Mono<NotificationDtoResponse> getDelayedDateTime(final int delayInMillis){
        return Mono.just(getCurrentDateTimeMessage())
                .map(notificationConverter::notificationStringToNotificationDtoResponse)
                .delayElement(Duration.ofMillis(delayInMillis));
    }

    public Flux<NotificationDtoResponse> getDateTimeSSE(final int delay, final int count){
        return Flux.range(0, count).delayElements(Duration.ofMillis(delay))
                .map(n-> getCurrentDateTimeMessage()+" ")
                .map(notificationConverter::notificationStringToNotificationDtoResponse)
                .onBackpressureBuffer();
    }
}

package org.acme.notification.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.notification.dto.NotificationDtoResponse;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;

@ApplicationScoped
@AllArgsConstructor
public class NotificationService {

    private static final String CURRENT_DATETIME = "Current datetime: ";

    private final NotificationConverter notificationConverter;

    private String getCurrentDateTimeMessage(){
        String localDateTime = String.valueOf(java.time.LocalDateTime.now());
        return CURRENT_DATETIME + localDateTime;
    }

    public Uni<NotificationDtoResponse> getDateTime(){
        return Uni.createFrom().item(getCurrentDateTimeMessage())
                .map(notificationConverter::notificationStringToNotificationDtoResponse);
    }

    public Uni<NotificationDtoResponse> getDelayedDateTime(final int delayInMillis){
        return Uni.createFrom().item(getCurrentDateTimeMessage()).onItem().delayIt()
                .by(Duration.ofMillis(delayInMillis))
                .map(notificationConverter::notificationStringToNotificationDtoResponse);
    }

    public Multi<NotificationDtoResponse> getDateTimeSSE(final int delay, final int count){
        return Multi.createFrom()
                .range(0,count)
                .onItem()
                .call(number -> Uni.createFrom()
                        .item(number)
                        .onItem()
                        .delayIt()
                        .by(Duration.ofMillis(delay)))
                .map(n-> getCurrentDateTimeMessage()+" ")
                .map(notificationConverter::notificationStringToNotificationDtoResponse)
                .onOverflow()
                .buffer();
    }
}

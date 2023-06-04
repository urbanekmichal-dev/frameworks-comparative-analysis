package com.example.notification.controller;

import com.example.notification.dto.NotificationDtoResponse;
import com.example.notification.service.NotificationService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Controller("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @Get("/datetime")
    @Status(HttpStatus.OK)
    public Mono<HttpResponse<NotificationDtoResponse>> getDateTime(){
        return notificationService.getDateTime().map(HttpResponse::ok);
    }

    @Get("/datetime/{delay}")
    public Mono<HttpResponse<NotificationDtoResponse>> getDelayedDateTime(@PathVariable final int delay){
        return notificationService.getDelayedDateTime(delay).map(HttpResponse::ok);
    }

    @Get("/datetimeSSE")
    @Produces(MediaType.TEXT_EVENT_STREAM)
    @Status(HttpStatus.OK)
    public Flux<NotificationDtoResponse> getDateTimeSSE(@QueryValue final int delay, @QueryValue final int count){
        return notificationService.getDateTimeSSE(delay, count);
    }
}

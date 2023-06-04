package org.acme.notification.controller;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;
import org.acme.notification.dto.NotificationDtoResponse;
import org.acme.notification.service.NotificationService;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@AllArgsConstructor
@Path("/notifications")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class NotificationController {
    private final NotificationService notificationService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/datetime")
    public Uni<RestResponse<NotificationDtoResponse>> getDateTime(){
        return notificationService.getDateTime().map(RestResponse::ok);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/datetime/{delay}")
    public Uni<RestResponse<NotificationDtoResponse>> getDelayedDateTime(@PathParam("delay") final int delay){
        return notificationService.getDelayedDateTime(delay).map(RestResponse::ok);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/datetimeSSE")
    @ResponseStatus(200)
    public Multi<NotificationDtoResponse> getDateTimeSSE(@QueryParam("delay") final int delay, @QueryParam("count") final int count){
        return notificationService.getDateTimeSSE(delay, count);
    }
}

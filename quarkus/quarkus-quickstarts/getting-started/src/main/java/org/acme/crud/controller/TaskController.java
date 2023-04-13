package org.acme.crud.controller;

import io.smallrye.mutiny.Uni;
import org.acme.crud.dto.TaskDtoRequest;
import org.acme.crud.dto.TaskDtoResponse;
import org.acme.crud.service.TaskService;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/tasks")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Uni<TaskDtoResponse> addTasks(final TaskDtoRequest taskDtoRequest){
        return taskService.addTask(taskDtoRequest);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{taskId}")
    public Uni<RestResponse<TaskDtoResponse>> getTask(@PathParam("taskId") final UUID taskId){
        return taskService.getTest(taskId)
                .map(RestResponse::ok).
                onFailure().recoverWithItem(RestResponse.notFound());
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{taskId}")
    public Uni<RestResponse<Void>> deleteTask(@PathParam("taskId") final UUID taskId){
        return  taskService.deleteTask(taskId).map(deleted -> deleted
                ? RestResponse.noContent()
                : RestResponse.notFound());
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{taskId}")
    public Uni<RestResponse<TaskDtoResponse>> updateTask(@PathParam("taskId") final UUID taskId,final TaskDtoRequest taskDtoRequest){
        return taskService.updateTask(taskId,taskDtoRequest).map(RestResponse::ok);
    }

}

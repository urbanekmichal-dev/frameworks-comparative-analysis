package org.acme.crud.coverter;

import org.acme.crud.dto.TaskDtoRequest;
import org.acme.crud.dto.TaskDtoResponse;
import org.acme.crud.entity.TaskEntity;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TaskConverter {
    public TaskDtoResponse convertTaskEntityToTaskDtoResponse(final TaskEntity source){
        final TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setTaskId(source.getId());
        taskDtoResponse.setName(source.getName());
        taskDtoResponse.setDescription(source.getDescription());
        return taskDtoResponse;
    }
    public TaskEntity convertTaskDtoRequestToTaskEntity(final TaskDtoRequest source){
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(source.getName());
        taskEntity.setDescription(source.getDescription());
        return taskEntity;
    }
    public TaskDtoResponse convertTaskDtoRequestToTaskDtoResponse(final TaskDtoRequest source){
        final TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setName(source.getName());
        taskDtoResponse.setDescription(source.getDescription());
        return taskDtoResponse;
    }
}

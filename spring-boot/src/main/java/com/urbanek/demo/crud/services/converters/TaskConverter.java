package com.urbanek.demo.crud.services.converters;

import com.urbanek.demo.crud.dto.TaskDtoRequest;
import com.urbanek.demo.crud.dto.TaskDtoResponse;
import com.urbanek.demo.crud.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public TaskDtoResponse convertTaskEntityToTaskDtoResponse(final TaskEntity source){
        final TaskDtoResponse taskDtoRequest = new TaskDtoResponse();
        taskDtoRequest.setTaskId(source.getTaskId());
        taskDtoRequest.setName(source.getName());
        taskDtoRequest.setDescription(source.getDescription());
        return taskDtoRequest;
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

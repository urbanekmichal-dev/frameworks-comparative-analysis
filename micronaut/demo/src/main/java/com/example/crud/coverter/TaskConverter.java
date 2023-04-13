package com.example.crud.coverter;

import com.example.crud.dto.TaskDtoRequest;
import com.example.crud.dto.TaskDtoResponse;
import com.example.crud.entity.TaskEntity;
import jakarta.inject.Singleton;

@Singleton
public class TaskConverter {
    public TaskDtoResponse convertTaskEntityToTaskDtoResponse(final TaskEntity source){
        final TaskDtoResponse taskDtoResponse = new TaskDtoResponse();
        taskDtoResponse.setId(source.getId());
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

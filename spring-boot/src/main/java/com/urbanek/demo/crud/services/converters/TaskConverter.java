package com.urbanek.demo.crud.services.converters;

import com.urbanek.demo.crud.dto.TaskDto;
import com.urbanek.demo.crud.dto.TaskDtoCreateResponse;
import com.urbanek.demo.crud.entity.TaskEntity;
import org.springframework.stereotype.Component;

@Component
public class TaskConverter {
    public TaskDto convertTaskEntityToTaskDto(final TaskEntity source){
        final TaskDto taskDto = new TaskDto();
        taskDto.setName(source.getName());
        taskDto.setDescription(source.getDescription());
        return taskDto;
    }
    public TaskEntity convertTaskDtoToTaskEntity(final TaskDto source){
        final TaskEntity taskEntity = new TaskEntity();
        taskEntity.setName(source.getName());
        taskEntity.setDescription(source.getDescription());
        return taskEntity;
    }
    public TaskDtoCreateResponse convertTaskDtoToTaskEntity(final TaskEntity source){
        final TaskDtoCreateResponse taskDtoCreateResponse = new TaskDtoCreateResponse();
        taskDtoCreateResponse.setTaskId(source.getTaskId());
        taskDtoCreateResponse.setName(source.getName());
        taskDtoCreateResponse.setDescription(source.getDescription());
        return taskDtoCreateResponse;
    }
}

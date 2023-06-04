package org.acme.crud.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskDtoResponse {
    private UUID taskId;
    private String name;
    private String description;
}

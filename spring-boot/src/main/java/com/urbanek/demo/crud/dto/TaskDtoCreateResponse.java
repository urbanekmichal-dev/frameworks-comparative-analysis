package com.urbanek.demo.crud.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
public class TaskDtoCreateResponse {
    private UUID taskId;
    private String name;
    private String description;
}

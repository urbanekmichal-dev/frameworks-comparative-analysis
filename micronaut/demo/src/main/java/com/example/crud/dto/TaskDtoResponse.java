package com.example.crud.dto;

import io.micronaut.core.annotation.Introspected;
import lombok.Data;

import java.util.UUID;

@Data
public class TaskDtoResponse {
    private Long id;
    private String name;
    private String description;
}

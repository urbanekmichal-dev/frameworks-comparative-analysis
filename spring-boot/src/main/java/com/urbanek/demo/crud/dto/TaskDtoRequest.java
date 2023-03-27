package com.urbanek.demo.crud.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@Data
public class TaskDtoRequest {
    private String name;
    private String description;
}

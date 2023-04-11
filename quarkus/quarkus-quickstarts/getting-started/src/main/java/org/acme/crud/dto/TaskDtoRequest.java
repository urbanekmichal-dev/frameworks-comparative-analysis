package org.acme.crud.dto;

import lombok.Data;

@Data
public class TaskDtoRequest {
    private String name;
    private String description;
}

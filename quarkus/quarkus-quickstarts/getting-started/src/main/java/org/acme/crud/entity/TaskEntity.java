package org.acme.crud.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor

public class TaskEntity {

    @Id
    @Column(insertable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private String description;
}

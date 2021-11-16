package com.rabbi.data.validator.repository;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class TestEntity {
    @Id
    private String id;

    private String name;
}

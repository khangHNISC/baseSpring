package com.example.flyway.core;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "core")
public class Cup {
    @Id
    int id;

    String name;

    public Cup() {}

    public Cup(String name) {
        this.name = name;
    }
}

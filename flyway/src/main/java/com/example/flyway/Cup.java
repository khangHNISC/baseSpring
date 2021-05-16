package com.example.flyway;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cup {
    @Id
    int id;

    String name;

    String age;
}

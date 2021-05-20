package com.example.flyway.reconcile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "reconcile")
public class Customer {
    @Id
    int id;

    public Customer() {
    }

    public Customer(String name, String age) {
        this.name = name;
        this.age = age;
    }

    String name;

    String age;
}


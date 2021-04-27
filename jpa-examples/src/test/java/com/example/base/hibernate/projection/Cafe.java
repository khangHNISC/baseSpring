package com.example.base.hibernate.projection;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by khangld5 on Apr 27, 2021
 */
@Entity(name = "CAFE")
@NoArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue
    private Long id;

    String name;

    public Cafe(String name) {
        this.name = name;
    }
}

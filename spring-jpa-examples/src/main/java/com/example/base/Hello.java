package com.example.base;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by khangld5 on Apr 20, 2021
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hello {
    @Id
    public Long id;
}

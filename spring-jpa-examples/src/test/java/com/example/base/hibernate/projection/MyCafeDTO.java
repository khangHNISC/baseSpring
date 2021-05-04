package com.example.base.hibernate.projection;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import javax.persistence.Tuple;

/**
 * Created by khangld5 on Apr 27, 2021
 */
@Value
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MyCafeDTO {
    Long id;
    String title;

    public MyCafeDTO(Tuple tuple) {
        this.id = tuple.get("id", Number.class).longValue();
        this.title = tuple.get("title", String.class);
    }
}

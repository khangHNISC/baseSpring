package com.example.base.hibernate.projection;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by khangld5 on Apr 27, 2021
 */
@NamedNativeQuery(
        name = "Cafe.getCafes",
        query = "SELECT c.id AS id, c.name AS title FROM CAFE c",
        resultSetMapping = "MyCafeDTOMapping"
)
@SqlResultSetMapping(
        name = "MyCafeDTOMapping",
        classes = @ConstructorResult(
                targetClass = MyCafeDTO.class,
                columns = {
                        @ColumnResult(name = "id", type = Long.class),
                        @ColumnResult(name = "title", type = String.class)
                }
        )
)
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

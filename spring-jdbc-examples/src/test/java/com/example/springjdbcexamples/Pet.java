package com.example.springjdbcexamples;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@ToString
@AllArgsConstructor(staticName = "create")
public class Pet {

    @Id
    private final Long id;
    private final String name;
    private final LocalDate birthDate;
}

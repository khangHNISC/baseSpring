package com.example.graphqlexamples.services;

import com.example.graphqlexamples.models.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class PersonService {
    public Collection<Person> findAll() {
        Collection<Person> people = new ArrayList<>();
        Person p = new Person(1L, "kien");
        people.add(p);
        return people;
    }
}

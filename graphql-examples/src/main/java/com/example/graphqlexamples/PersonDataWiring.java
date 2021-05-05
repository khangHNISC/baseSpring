package com.example.graphqlexamples;

import com.example.graphqlexamples.services.PersonService;
import graphql.schema.idl.RuntimeWiring;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.boot.RuntimeWiringCustomizer;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersonDataWiring implements RuntimeWiringCustomizer {
    @Autowired
    private PersonService personService;

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.type("Query", typeWiring -> typeWiring
                .dataFetcher("people", env -> this.personService.findAll()));
    }
}

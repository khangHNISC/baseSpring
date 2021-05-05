package com.example.graphqlexamples.wiring;

import com.example.graphqlexamples.services.PersonService;
import graphql.schema.idl.RuntimeWiring;
import lombok.RequiredArgsConstructor;;
import org.springframework.graphql.boot.RuntimeWiringCustomizer;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PersonDataWiring implements RuntimeWiringCustomizer {
    private final PersonService personService;

    @Override
    public void customize(RuntimeWiring.Builder builder) {
        builder.type("Query", typeWiring -> typeWiring
                .dataFetcher("people", env -> this.personService.findAll()));
    }
}

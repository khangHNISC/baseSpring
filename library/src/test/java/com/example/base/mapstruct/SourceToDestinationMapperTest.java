package com.example.base.mapstruct;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class SourceToDestinationMapperTest {

    SourceToDestinationMapper mapper = Mappers.getMapper(SourceToDestinationMapper.class);

    @Test
    void givenSimpleData_MapCorrectly() {
        Source s = new Source();
        s.setName("khang");

        Destination d = mapper.sourceToDestination(s);
        assertEquals("khang", d.getName());
    }

    @Test
    void givenMisMatch_MapCorrectly() {
        Source s = new Source();
        s.setSomeCrazyShit(12);

        Destination d = mapper.sourceToDestination(s);
        assertEquals(12, d.getAge());
        assertEquals(0, d.getVersion());
    }
}
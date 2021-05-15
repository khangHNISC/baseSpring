package com.example.base.mapstruct;

import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * 1. use @Mapper uses for child conversion
 * 2. if transform is complicated use abstract class instead
 */
@Mapper(componentModel = "spring")
public interface SourceToDestinationMapper {

    /**
     * dest and source are optional params
     */
    @BeforeMapping
    default void before(@MappingTarget Destination dest, Source source) {
        source.setCode("123");
        dest.setVersion(0L);
    }

    @Mapping(target = "age", source = "source.someCrazyShit")
    @Mapping(target = "startDate", source = "source.startDate", dateFormat = "dd-MM-yyyy HH:mm:ss")
    @Mapping(target = "extraStuff", expression = "java(source.getCode())")
    Destination sourceToDestination(Source source);

    /**
     * this append to all String -> String map :()
     */
    default String toString(String extraStuff) {
        return extraStuff;
    }
}

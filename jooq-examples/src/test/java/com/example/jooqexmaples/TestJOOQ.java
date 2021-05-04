package com.example.jooqexmaples;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import javax.sql.DataSource;

import java.util.List;

import static com.example.base.model.tables.Cup.CUP;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Created by khangld5 on Apr 29, 2021
 *
 * mark target as generated source
 */
@JdbcTest
@ExtendWith(SpringExtension.class)
class TestJOOQ {

    @Autowired
    DataSource dataSource;

    private DSLContext create;

    @BeforeEach
    void setup(){
        create = DSL.using(dataSource, SQLDialect.H2);
    }

    @Test
    void testSomeJOOQ() {
        assertNotNull(create);

        List<MyCup> cups = create.select().from(CUP).fetchInto(MyCup.class);
        assertFalse(cups.isEmpty());
    }

    private static class MyCup {
        public long id;
        public String name;
    }
}

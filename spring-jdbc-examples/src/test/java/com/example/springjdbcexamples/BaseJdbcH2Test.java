package com.example.springjdbcexamples;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@JdbcTest
@ExtendWith(SpringExtension.class)
public class BaseJdbcH2Test {
}

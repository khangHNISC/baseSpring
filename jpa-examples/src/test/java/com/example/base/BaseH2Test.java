package com.example.base;

import com.example.base.hibernate.association.one_to_one.UnidirectionalOneToOneTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by khangld5 on Apr 22, 2021
 * <p>
 * since there is no way for this test to detect only individuals testing entities,
 * all entities in this modules will be detected by hibernate.
 * <p>
 * Therefore the entity naming are required making up for duplicate entity class name.
 * The naming convention will be TestClassName + '$' + EntityClassName
 *
 * @see UnidirectionalOneToOneTest
 */
@DataJpaTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BaseH2Test {

    @Autowired
    protected TestEntityManager em;
}

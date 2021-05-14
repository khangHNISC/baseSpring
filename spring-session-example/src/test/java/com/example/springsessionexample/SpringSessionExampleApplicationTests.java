package com.example.springsessionexample;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
class SpringSessionExampleApplicationTests extends AbstractRedisTestContainer {

    @Test
    void isRedisOn() {
        assertNotNull(redisContainer);
    }

    @Test
    @WithMockUser
    void sessionIdNotNull() {
        testClient.get()
                .uri("/")
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.OK)
                .expectBody()
                .consumeWith(
                        response ->
                                assertThat(response.getResponseBody()).isNotNull()

                );
    }
}

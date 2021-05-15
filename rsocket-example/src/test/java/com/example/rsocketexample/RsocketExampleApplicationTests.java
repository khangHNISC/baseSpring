package com.example.rsocketexample;

import io.rsocket.metadata.WellKnownMimeType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.rsocket.metadata.SimpleAuthenticationEncoder;
import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class RsocketExampleApplicationTests {

    private static RSocketRequester requester;

    @BeforeAll
    public static void setRequester(
            @Autowired RSocketRequester.Builder builder,
            @LocalRSocketServerPort Integer port
    ) {
        UsernamePasswordMetadata credentials = new UsernamePasswordMetadata("user", "pass");
        MimeType mimeType = MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());
        requester = builder
                .setupMetadata(credentials, mimeType)
                .rsocketStrategies(s -> s.encoder(new SimpleAuthenticationEncoder()))
                .tcp("localhost", port);
    }

    @Test
    void contextLoads() {
        assertNotNull(requester);
    }

    @Test
    void testRequestGetsOnce() {
        Mono<Message> response = requester
                .route("request-response")
                .data(new Message("TEST"))
                .retrieveMono(Message.class);

        StepVerifier
                .create(response)
                .consumeNextWith(message ->
                        assertThat(message.getAMessage()).isEqualTo("You said: TEST"))
                .verifyComplete();
    }

    @Test
    void testFireAndForget() {
        Mono<Void> result = requester
                .route("fire-and-forget")
                .data(new Message("TEST"))
                .retrieveMono(Void.class);

        StepVerifier
                .create(result)
                .verifyComplete();
    }

    @Test
     void testRequestStream() {
        Flux<Message> stream = requester
                .route("request-stream")
                .data(new Message("TEST"))
                .retrieveFlux(Message.class);

        StepVerifier
                .create(stream)
                .consumeNextWith(message -> {
                    assertThat(message.getAMessage()).isEqualTo("You said: TEST. Response #0");
                })
                .expectNextCount(0)
                .consumeNextWith(message -> {
                    assertThat(message.getAMessage()).isEqualTo("You said: TEST. Response #1");
                })
                .thenCancel()
                .verify();
    }

    @Test
    void testStreamGetsStream() {
        Mono<Integer> setting1 = Mono.just(2).delayElement(Duration.ofSeconds(0));
        Mono<Integer> setting2 = Mono.just(1).delayElement(Duration.ofSeconds(3));
        Flux<Integer> settings = Flux.concat(setting1, setting2);

        // Send a stream of request messages
        Flux<Message> stream = requester
                .route("stream-stream")
                .data(settings)
                .retrieveFlux(Message.class);

        StepVerifier
                .create(stream)
                .consumeNextWith(message -> {
                    assertThat(message.getAMessage()).isEqualTo("Stream Response #0");
                })
                .consumeNextWith(message -> {
                    assertThat(message.getAMessage()).isEqualTo("Stream Response #0");
                })
                .thenCancel()
                .verify();
    }
}

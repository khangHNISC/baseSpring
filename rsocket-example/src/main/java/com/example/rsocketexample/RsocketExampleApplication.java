package com.example.rsocketexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RestController
@SpringBootApplication
class RsocketExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(RsocketExampleApplication.class, args);
    }

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("request-response")
    Mono<Message> requestResponse(final Message message, @AuthenticationPrincipal UserDetails user) {
        log.info("Received request-response message: {}", message);
        log.info("Request-response initiated by '{}' in the role '{}'", user.getUsername(), user.getAuthorities());
        return Mono.just(new Message("You said: " + message.getAMessage()));
    }

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("fire-and-forget")
    public Mono<Void> fireAndForget(final Message message, @AuthenticationPrincipal UserDetails user) {
        log.info("Received fire-and-forget request: {}", message);
        log.info("Fire-and-forget initiated by '{}' in the role '{}'", user.getUsername(), user.getAuthorities());
        return Mono.empty();
    }

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("request-stream")
    Flux<Message> stream(final Message message, @AuthenticationPrincipal UserDetails user) {
        log.info("Received stream request: {}", message);
        log.info("Request-stream initiated by '{}' in the role '{}'", user.getUsername(), user.getAuthorities());
        return Flux
                .interval(Duration.ofSeconds(1))
                .map(index -> new Message("You said: " + message.getAMessage() + ". Response #" + index))
                .log();
    }

    @PreAuthorize("hasRole('USER')")
    @MessageMapping("stream-stream")
    Flux<Message> channel(final Flux<Integer> settings, @AuthenticationPrincipal UserDetails user) {
        log.info("Received stream-stream (channel) request...");
        log.info("Stream-stream (channel) initiated by '{}' in the role '{}'", user.getUsername(), user.getAuthorities());
        return settings
                .doOnNext(setting -> log.info("Requested interval is {} seconds.", setting))
                .doOnCancel(() -> log.warn("The client cancelled the channel."))
                .switchMap(
                        setting -> Flux.interval(Duration.ofSeconds(setting))
                                .map(index -> new Message("Stream Response #" + index)))
                .log();
    }
}

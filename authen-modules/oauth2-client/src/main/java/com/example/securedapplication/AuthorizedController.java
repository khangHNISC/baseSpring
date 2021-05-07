package com.example.securedapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@Controller
@RequiredArgsConstructor
class AuthorizedController {

    private final WebClient webClient;

    @GetMapping("/")
    public String root() {
        return "redirect:/authorize?grant_type=authorization_code";
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public ResponseEntity<String[]> authorizationCodeGrant(
            @RegisteredOAuth2AuthorizedClient("messaging-client-authorization-code")
                    OAuth2AuthorizedClient authorizedClient) {
        String messagesBaseUri = "http://localhost:8090/messages";
        String[] messages = this.webClient
                .get()
                .uri(messagesBaseUri)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        return ResponseEntity.ok(messages);
    }
}

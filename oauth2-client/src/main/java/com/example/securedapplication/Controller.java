package com.example.securedapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
class Controller {

    private final RestTemplate restTemplate;

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public String authorizationCodeGrant(
            @RegisteredOAuth2AuthorizedClient("messaging-client-authorization-code")
                    OAuth2AuthorizedClient authorizedClient) {
        String uri = "http://localhost:8090/messages";
        ResponseEntity<String> responseEntity = this.restTemplate
                .exchange(uri, HttpMethod.GET, null, String.class);
        return responseEntity.getBody();
    }

    @GetMapping("/")
    public String getMessages(OAuth2AuthenticationToken token) {
        String uri = "http://localhost:8090/messages";

        ResponseEntity<String> responseEntity = this.restTemplate
                .exchange(uri, HttpMethod.GET, null, String.class);
        return responseEntity.getBody();
    }
}

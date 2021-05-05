package com.example.securedapplication;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
@RequiredArgsConstructor
class AuthorizedController {

    private final RestTemplate restTemplate;

    @GetMapping("/")
    public String root() {
        return "redirect:/authorize?grant_type=authorization_code";
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public ResponseEntity<String[]> authorizationCodeGrant(
            @RegisteredOAuth2AuthorizedClient("messaging-client-oidc")
                    OAuth2AuthorizedClient authorizedClient) {
        String uri = "http://localhost:8090/messages";
        return restTemplate.exchange(uri, HttpMethod.GET, null, String[].class);
    }
}

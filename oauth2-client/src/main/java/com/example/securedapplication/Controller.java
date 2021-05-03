package com.example.securedapplication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
class Controller {

    private final RestTemplate restTemplate;
    private final OAuth2AuthorizedClientService clientService;

    @GetMapping("/private")
    public String hello() {
        return "hello";
    }

    @GetMapping("/")
    public PrincipalDetails profile(OAuth2AuthenticationToken token) {
        OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(),
                token.getName());

        String uri = client.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUri();

        ResponseEntity<PrincipalDetails> responseEntity = this.restTemplate
                .exchange(uri, HttpMethod.GET, null, PrincipalDetails.class);
        return responseEntity.getBody();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class PrincipalDetails {
        private String name;
    }
}

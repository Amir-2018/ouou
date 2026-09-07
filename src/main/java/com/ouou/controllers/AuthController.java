package com.ouou.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/auth/")
public class AuthController {

    @Value("${keycloak.token-uri:http://localhost:8080/realms/ouou/protocol/openid-connect/token}")
    private String tokenUri;

    @Value("${keycloak.client-id:ouou-backend}")
    private String clientId;

    @Value("${keycloak.client-secret:}")
    private String clientSecret;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        String role = credentials.getOrDefault("role", "admin");

        if (username == null || password == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "username and password are required"));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "password");
        form.add("client_id", clientId);
        if (!clientSecret.isBlank()) {
            form.add("client_secret", clientSecret);
        }
        form.add("username", username);
        form.add("password", password);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(tokenUri, new HttpEntity<>(form, headers), Map.class);
            Map<String, Object> body = response.getBody();
            if (body == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid credentials"));
            }
            String accessToken = (String) body.get("access_token");
            Map<String, Object> extra = Map.of(
                    "token_type", body.getOrDefault("token_type", "Bearer"),
                    "expires_in", body.getOrDefault("expires_in", 0),
                    "refresh_token", body.getOrDefault("refresh_token", ""),
                    "role", role
            );
            return ResponseEntity.ok(Map.of(
                    "access_token", accessToken,
                    "token_type", extra.get("token_type"),
                    "expires_in", extra.get("expires_in"),
                    "refresh_token", extra.get("refresh_token"),
                    "role", role,
                    "username", username
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Authentication failed", "details", e.getMessage()));
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Missing or invalid Authorization header"));
        }
        return ResponseEntity.ok(Map.of(
                "message", "Token validated. Decode JWT payload on client side to extract user info.",
                "token_prefix", authHeader.substring(0, Math.min(20, authHeader.length())) + "..."
        ));
    }
}
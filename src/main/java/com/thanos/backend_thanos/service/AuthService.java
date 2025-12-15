package com.thanos.backend_thanos.service;

import com.thanos.backend_thanos.dto.response.AuthResponse;
import com.thanos.backend_thanos.dto.request.LoginRequest;
import com.thanos.backend_thanos.dto.request.SignUpRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.anon-key}")
    private String supabaseAnonKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public AuthResponse login(LoginRequest request) {
        String url = supabaseUrl + "/auth/v1/token?grant_type=password";

        HttpHeaders headers = createHeaders();
        Map<String, String> body = new HashMap<>();
        body.put("email", request.email());
        body.put("password", request.password());

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<AuthResponse> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                AuthResponse.class
        );

        return response.getBody();
    }

    public Map<String, Object> signUp(SignUpRequest request) {
        String url = supabaseUrl + "/auth/v1/signup";

        HttpHeaders headers = createHeaders();
        Map<String, String> body = new HashMap<>();
        body.put("email", request.email());
        body.put("password", request.password());

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        ParameterizedTypeReference<Map<String, Object>> responseType =
                new ParameterizedTypeReference<>() {};

        ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                entity,
                responseType
        );

        return response.getBody();
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("apikey", supabaseAnonKey);
        return headers;
    }
}
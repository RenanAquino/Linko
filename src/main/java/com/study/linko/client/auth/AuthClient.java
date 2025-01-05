package com.study.linko.client.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.study.linko.dto.auth.LoginDTO;
import com.study.linko.dto.auth.RegisterDTO;

@Component
public class AuthClient {

    @Autowired
    RestTemplate restTemplate;

    private final String REGISTER_URI = "http://localhost:8081/user/register";
    private final String LOGIN_URI = "http://localhost:8081/auth/login";

    public void register(RegisterDTO data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterDTO> request = new HttpEntity<>(data, headers);

        restTemplate.postForObject(REGISTER_URI, request, Void.class);
    }

    public String login(LoginDTO data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LoginDTO> request = new HttpEntity<>(data, headers);

        return restTemplate.postForObject(LOGIN_URI, request, String.class);
    }
}

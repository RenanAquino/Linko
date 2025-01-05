package com.study.linko.client.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import com.study.linko.dto.auth.LoginDTO;
import com.study.linko.dto.auth.RegisterDTO;

public class AuthClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private AuthClient authClient;

    private final String REGISTER_URI = "http://localhost:8081/auth/register";
    private final String LOGIN_URI = "http://localhost:8081/auth/login";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO("user", "user@gmail.com", "123456");
        
        authClient.register(registerDTO);

        verify(restTemplate).postForObject(eq(REGISTER_URI), any(HttpEntity.class), eq(Void.class));
    }

    @Test
    public void testLogin() {
        LoginDTO loginDTO = new LoginDTO("user@gmail.com", "123456");
        String expectedResponse = "token";
        
        when(restTemplate.postForObject(eq(LOGIN_URI), any(HttpEntity.class), eq(String.class))).thenReturn(expectedResponse);
        
        String response = authClient.login(loginDTO);

        verify(restTemplate).postForObject(eq(LOGIN_URI), any(HttpEntity.class), eq(String.class));

        assertEquals(expectedResponse, response);
    }
}

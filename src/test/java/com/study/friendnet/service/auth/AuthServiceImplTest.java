package com.study.friendnet.service.auth;

import com.study.friendnet.client.auth.AuthClient;
import com.study.friendnet.dto.MailDTO;
import com.study.friendnet.dto.auth.LoginDTO;
import com.study.friendnet.dto.auth.RegisterDTO;
import com.study.friendnet.message.mail.KafkaMailProducerMessage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

class AuthServiceImplTest {

    private AuthServiceImpl authService;

    @Mock
    private AuthClient authClient;

    @Mock
    private KafkaMailProducerMessage kafkaAuthProducerMessage;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(authClient, kafkaAuthProducerMessage);
    }

    @Test
    void testRegister() {
        RegisterDTO registerDTO = new RegisterDTO("user", "user@example.com", "password");
        MailDTO expectedAuthMailDTO = new MailDTO("user@example.com", "Olá, " + registerDTO.username() + "! Bem-vindo à FriendNet", "Sua conta foi criada com sucesso!");

        authService.register(registerDTO);

        verify(authClient, times(1)).register(registerDTO);
        verify(kafkaAuthProducerMessage, times(1)).sendmail(expectedAuthMailDTO);
    }

    @Test
    void testLogin() {
        LoginDTO loginDTO = new LoginDTO("user@example.com", "password");
        String expectedResult = "success";
        MailDTO expectedAuthMailDTO = new MailDTO("user@example.com", "Aviso de login", "Você fez login na sua conta com o email: user@example.com");

        when(authClient.login(loginDTO)).thenReturn(expectedResult);

        String result = authService.login(loginDTO);

        verify(authClient, times(1)).login(loginDTO);
        verify(kafkaAuthProducerMessage, times(1)).sendmail(expectedAuthMailDTO);
        assertEquals(expectedResult, result);
    }
}

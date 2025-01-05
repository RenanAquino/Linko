package com.study.linko.service.auth;

import org.springframework.stereotype.Service;

import com.study.linko.client.auth.AuthClient;
import com.study.linko.dto.MailDTO;
import com.study.linko.dto.auth.LoginDTO;
import com.study.linko.dto.auth.RegisterDTO;
import com.study.linko.message.mail.KafkaMailProducerMessage;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;
    private final KafkaMailProducerMessage kafkaAuthProducerMessage;

    public AuthServiceImpl(AuthClient authClient, KafkaMailProducerMessage kafkaAuthProducerMessage) {
        this.authClient = authClient;
        this.kafkaAuthProducerMessage = kafkaAuthProducerMessage;
    }

    @Override
    public void register(RegisterDTO data) {
        authClient.register(data);
        MailDTO authMailDTO = new MailDTO(data.email(), "Sua conta foi criada com sucesso!", "Olá, " + data.username() + "! Bem-vindo ao Linko!");
        kafkaAuthProducerMessage.sendmail(authMailDTO);
    }

    @Override
    public String login(LoginDTO data) {
        MailDTO authMailDTO = new MailDTO(data.email(), "Aviso de login", "Você fez login na sua conta com o email: " + data.email());
        kafkaAuthProducerMessage.sendmail(authMailDTO);
        return "result";
    }
}
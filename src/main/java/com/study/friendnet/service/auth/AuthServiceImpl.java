package com.study.friendnet.service.auth;

import org.springframework.stereotype.Service;

import com.study.friendnet.client.auth.AuthClient;
import com.study.friendnet.dto.MailDTO;
import com.study.friendnet.dto.auth.LoginDTO;
import com.study.friendnet.dto.auth.RegisterDTO;
import com.study.friendnet.message.mail.KafkaMailProducerMessage;

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
        MailDTO authMailDTO = new MailDTO(data.email(), "Olá, " + data.username() + "! Bem-vindo à FriendNet", "Sua conta foi criada com sucesso!");
        kafkaAuthProducerMessage.sendmail(authMailDTO);
    }

    @Override
    public String login(LoginDTO data) {
        String result = authClient.login(data);
        MailDTO authMailDTO = new MailDTO(data.email(), "Aviso de login", "Você fez login na sua conta com o email: " + data.email());
        kafkaAuthProducerMessage.sendmail(authMailDTO);
        return result;
    }
}
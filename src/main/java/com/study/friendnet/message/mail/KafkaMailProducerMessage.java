package com.study.friendnet.message.mail;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.study.friendnet.dto.MailDTO;


@Component
public class KafkaMailProducerMessage {

    private final KafkaTemplate<String, MailDTO> mailDTOTemplate;

    public KafkaMailProducerMessage(KafkaTemplate<String, MailDTO> mailDTOTemplate) {
        this.mailDTOTemplate = mailDTOTemplate;
    }

    public void sendmail(MailDTO mailDTO) {
        mailDTOTemplate.send("mail_topic", mailDTO);
    }
}
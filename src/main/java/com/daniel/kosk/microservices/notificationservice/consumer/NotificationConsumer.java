package com.daniel.kosk.microservices.notificationservice.consumer;

import com.daniel.kosk.microservices.notificationservice.dto.ClientActivationDto;
import com.daniel.kosk.microservices.notificationservice.service.NotificationSendingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {
    @Autowired
    private ObjectMapper jacksonObjectMapper;
    @Autowired
    private NotificationSendingService notificationSendingService;



    @KafkaListener(topics = "notification-topic",groupId = "notification-service-group")
    public void consumeClientActivationMessage(String message) {
        try{
            ClientActivationDto clientActivationDto = jacksonObjectMapper.readValue(message,ClientActivationDto.class);
            try {
                notificationSendingService.sendActivationLink(clientActivationDto.email(), clientActivationDto.activationLink());
            }catch (Exception e){
                log.error(e.getMessage());
            }
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
        }
    }
}

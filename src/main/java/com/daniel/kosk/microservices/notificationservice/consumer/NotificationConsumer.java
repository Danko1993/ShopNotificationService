package com.daniel.kosk.microservices.notificationservice.consumer;

import com.daniel.kosk.microservices.notificationservice.config.RabbitMQConfig;
import com.daniel.kosk.microservices.notificationservice.dto.ClientActivationDto;
import com.daniel.kosk.microservices.notificationservice.dto.UpdateClientMessageDto;
import com.daniel.kosk.microservices.notificationservice.service.NotificationSendingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationConsumer {

    private final NotificationSendingService notificationSendingService;


    public NotificationConsumer(NotificationSendingService notificationSendingService, RabbitMQConfig rabbitMQConfig) {
        this.notificationSendingService = notificationSendingService;

    }

    @RabbitListener(queues = RabbitMQConfig.ACTIVATION_QUEUE )
    public void receiveActivationData(ClientActivationDto clientActivationDto) {
        try {
            notificationSendingService.sendActivationLink(clientActivationDto.email(),clientActivationDto.activationLink());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @RabbitListener(queues = RabbitMQConfig.CLIENT_UPDATE_QUEUE)
    public void receiveClientUpdateData(UpdateClientMessageDto updateClientMessageDto) {
        log.info("Received update client message {}", updateClientMessageDto);
        try {
            notificationSendingService.sendUpdateConfirmation(updateClientMessageDto.getEmail(), updateClientMessageDto.getDataName(), updateClientMessageDto.getOldValue(), updateClientMessageDto.getNewValue());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}

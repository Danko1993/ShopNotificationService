package com.daniel.kosk.microservices.notificationservice.consumer;

import com.daniel.kosk.microservices.notificationservice.config.RabbitMQConfig;
import com.daniel.kosk.microservices.notificationservice.dto.ClientActivationDto;
import com.daniel.kosk.microservices.notificationservice.service.NotificationSendingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    private final NotificationSendingService notificationSendingService;


    public NotificationConsumer(NotificationSendingService notificationSendingService, RabbitMQConfig rabbitMQConfig) {
        this.notificationSendingService = notificationSendingService;

    }

    @RabbitListener(queues = RabbitMQConfig.NOTIFICATION_QUEUE_NAME )
    public void receiveNotificationData(ClientActivationDto clientActivationDto) {
        try {
            notificationSendingService.sendActivationLink(clientActivationDto.email(),clientActivationDto.activationLink());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

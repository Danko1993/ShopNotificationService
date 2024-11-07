package com.daniel.kosk.microservices.notificationservice.dto;

import lombok.Data;

@Data
public class UpdateClientMessageDto {
    private String email;
    private String dataName;
    private String oldValue;
    private String newValue;
}

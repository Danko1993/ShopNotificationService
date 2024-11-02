package com.daniel.kosk.microservices.notificationservice.dto;

public record ClientActivationDto(
        String email,
        String activationLink
) {
}

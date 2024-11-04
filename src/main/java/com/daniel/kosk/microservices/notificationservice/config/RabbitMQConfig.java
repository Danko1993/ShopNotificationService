package com.daniel.kosk.microservices.notificationservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;



@Configuration
public class RabbitMQConfig {
    public static final String NOTIFICATION_QUEUE_NAME = "notification_queue";
    public static final String USER_SAVED_EXCHANGE = "user_saved_exchange";
    public static final String USER_SAVED_KEY = "user_saved_key";



    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME,true);
    }
    @Bean
    public TopicExchange userSavedExchange() {
        return new TopicExchange(USER_SAVED_EXCHANGE);
    }
    @Bean
    public Binding userSavedBinding() {
        return BindingBuilder.bind(notificationQueue()).to(userSavedExchange()).with(USER_SAVED_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}

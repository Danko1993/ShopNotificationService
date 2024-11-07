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
    public static final String ACTIVATION_QUEUE = "activation_queue";
    public static final String USER_SAVED_EXCHANGE = "user_saved_exchange";
    public static final String USER_SAVED_KEY = "user_saved_key";
    public static final String CLIENT_UPDATE_QUEUE = "client_update_queue";
    public static final String CLIENT_UPDATE_EXCHANGE = "client_update_exchange";
    public static final String CLIENT_UPDATE_KEY = "client_update_key";



    @Bean
    public Queue activationQueue() {
        return new Queue(ACTIVATION_QUEUE,true);
    }
    @Bean
    public TopicExchange userSavedExchange() {
        return new TopicExchange(USER_SAVED_EXCHANGE);
    }
    @Bean
    public Binding userSavedBinding() {
        return BindingBuilder.bind(activationQueue()).to(userSavedExchange()).with(USER_SAVED_KEY);
    }

    @Bean
    public Queue clientUpdateQueue() {
        return new Queue(CLIENT_UPDATE_QUEUE);
    }

    @Bean
    public TopicExchange clientUpdateExchange() {
        return new TopicExchange(CLIENT_UPDATE_EXCHANGE);
    }
    @Bean
    public Binding clientUpdateBinding() {
        return BindingBuilder.bind(clientUpdateQueue()).to(clientUpdateExchange()).with(CLIENT_UPDATE_KEY);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}

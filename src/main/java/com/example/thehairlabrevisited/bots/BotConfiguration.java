package com.example.thehairlabrevisited.bots;

import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

//@Configuration
public class BotConfiguration {

    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient getGatewayDiscordClient(List<EventListener<T>> eventListeners){
        GatewayDiscordClient client =  DiscordClientBuilder.create(token)
                    .build()
                    .login()
                    .block();

        for(EventListener<T> eventListener: eventListeners){
            assert client != null;
            client.on(eventListener.getEventType())
                    .flatMap(eventListener::execute)
                    .onErrorResume(eventListener::handleError)
                    .subscribe();
        }

        return client;
    }
}

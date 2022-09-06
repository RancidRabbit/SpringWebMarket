package ru.gb.Ex.webApp.configs;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry configure) {
        /* ответные сообщения будут уходить в эндпоинты, начинающиеся с /topic */
       configure.enableSimpleBroker("/topic");

       /* This prefix will be used to define all the message mappings. For example,
       /app/hello is the endpoint that the GreetingController.greeting() method is mapped to handle. */
       configure.setApplicationDestinationPrefixes("/app");
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
         registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }



}

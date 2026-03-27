package com.example.concurrent_seat_booking.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        /*
         * Activamos un broker que gestiona los mensajes
         * que el backend envia a los clientes suscritos
         * 
         * Establecemos un prefijo para que el
         * cliente pueda enviarm mensajes al backend
         */
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        /*
         * Establecemos el endpoint para iniciar la
         * comunicación con el frontend mediante WebSocket
         * 
         * Permitimos conexion solo desde nuestro frontend
         * e indicamos el método que debe usar
         */
        registry.addEndpoint("/ws")
                .setAllowedOriginPatterns("http://localhost:5173/")
                .withSockJS();
    }

}

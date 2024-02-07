package ru.gov.ac.isorv.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.DefaultContentTypeResolver;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import java.util.List;

/**
 * Конфигурация WebSocket и STOMP для приложения.
 * Включает настройки брокера сообщений и эндпоинтов для подключения клиентов.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic"); // Включает простой брокер сообщений для подписки на топики
        registry.setApplicationDestinationPrefixes("/app"); // Устанавливает префикс для отправки сообщений серверу
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // Регистрирует эндпоинт для подключения к WebSocket
                .setAllowedOriginPatterns("*") // Разрешает подключения со всех источников
                .withSockJS(); // Включает поддержку SockJS для клиентов без WebSocket
    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> messageConverters) {
        DefaultContentTypeResolver resolver = new DefaultContentTypeResolver();
        resolver.setDefaultMimeType(MimeTypeUtils.APPLICATION_JSON); // Устанавливает MIME тип по умолчанию
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule()); // Регистрирует модуль для корректной работы с Java 8 DateTime
        converter.setObjectMapper(mapper);
        converter.setContentTypeResolver(resolver);
        messageConverters.add(converter); // Добавляет конвертер для обработки JSON сообщений
        return false;
    }
}


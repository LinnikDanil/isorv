package ru.gov.ac.isorv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Конфигурация CORS для глобального уровня приложения.
 * Эта конфигурация позволяет обрабатывать CORS (Cross-Origin Resource Sharing) запросы
 * со всех источников для всех маршрутов и методов.
 */
@Configuration
public class GlobalCorsConfig {
    /**
     * Создает и конфигурирует {@link CorsFilter} с настройками CORS для обработки запросов.
     *
     * @return {@link CorsFilter} для использования в контексте Spring.
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // Разрешает cookies и аутентификационные данные
        config.addAllowedOriginPattern("*"); // Разрешает запросы со всех источников
        config.addAllowedHeader("*"); // Разрешает все заголовки
        config.addAllowedMethod("*"); // Разрешает все методы HTTP запросов
        source.registerCorsConfiguration("/**", config); // Применяет настройки ко всем маршрутам
        return new CorsFilter(source);
    }
}

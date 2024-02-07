package ru.gov.ac.isorv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Главный класс приложения Spring Boot.
 * Запускает приложение и инициализирует контекст Spring.
 */
@SpringBootApplication
public class IsorvApplication {

    public static void main(String[] args) {
        SpringApplication.run(IsorvApplication.class, args);
    }

}
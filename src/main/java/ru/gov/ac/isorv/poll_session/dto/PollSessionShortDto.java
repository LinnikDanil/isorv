package ru.gov.ac.isorv.poll_session.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Краткое DTO для представления сессии опроса.
 * Используется для передачи базовой информации о сессии опроса.
 */
@Data
@AllArgsConstructor
public class PollSessionShortDto {
    private Long id; // Уникальный идентификатор сессии
    private String pollTitle; // Название опроса
}


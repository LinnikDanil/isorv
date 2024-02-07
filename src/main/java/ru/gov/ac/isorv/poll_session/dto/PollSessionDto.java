package ru.gov.ac.isorv.poll_session.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * DTO для передачи данных сессии опроса.
 * Содержит информацию о текущем статусе сессии опроса.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollSessionDto {
    Long pollSessionId; // Уникальный идентификатор сессии
    Integer currentQuestionNumber; // Номер текущего вопроса в опросе
    Integer questionNumber; // Общее количество вопросов в опросе
}

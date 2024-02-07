package ru.gov.ac.isorv.poll_session.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;

import java.util.List;

/**
 * DTO для передачи данных сессии опроса пользователям.
 * Содержит детальную информацию о текущем вопросе сессии.
 */
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PollSessionUserDto {
    Long pollSessionId; // Уникальный идентификатор сессии
    Integer currentQuestionNumber; // Номер текущего вопроса
    Integer questionsNumber; // Общее количество вопросов в опросе

    Long questionId; // Уникальный идентификатор текущего вопроса
    String questionType; // Тип текущего вопроса
    String questionText; // Текст текущего вопроса

    List<ResponseOptionDto> options; // Список вариантов ответа для текущего вопроса
}

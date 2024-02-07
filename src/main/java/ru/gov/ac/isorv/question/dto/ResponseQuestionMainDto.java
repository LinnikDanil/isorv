package ru.gov.ac.isorv.question.dto;

import lombok.Builder;
import lombok.Data;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;

import java.util.List;

/**
 * DTO для представления вопроса на главном экране.
 * Содержит информацию о текущем вопросе, общем количестве вопросов,
 * типе вопроса, тексте вопроса и список вариантов ответа.
 */
@Data
@Builder
public class ResponseQuestionMainDto {
    Integer currentQuestionNumber; // Номер текущего вопроса
    Integer questionsNumber; // Общее количество вопросов в опросе

    String questionType; // Тип текущего вопроса
    String questionText; // Текст текущего вопроса

    List<ResponseOptionDto> options; // Список DTO вариантов ответа на текущий вопрос
}

package ru.gov.ac.isorv.question.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для представления вопроса.
 * Содержит идентификатор, порядковый номер, текст вопроса и его тип.
 */
@Data
@Builder
public class ResponseQuestionDto {
    long id; // Идентификатор вопроса
    int order_index; // Порядковый номер вопроса в опросе
    String text; // Текст вопроса
    String type; // Тип вопроса
}

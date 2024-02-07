package ru.gov.ac.isorv.response.dto;

import lombok.Data;

import java.util.Set;

/**
 * DTO для создания ответа пользователя.
 * Содержит идентификаторы выбранных вариантов ответа и/или текст ответа для открытых вопросов.
 */
@Data
public class CreateResponseDto {
    Set<Long> selectedOptionIds; // Идентификаторы выбранных вариантов ответа
    private String text; // Текст ответа пользователя
}

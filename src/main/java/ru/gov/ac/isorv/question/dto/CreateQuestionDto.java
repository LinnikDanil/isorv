package ru.gov.ac.isorv.question.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для создания нового вопроса.
 * Содержит текст вопроса, его тип и список вариантов ответов.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuestionDto {
    private List<String> options; // Список текстов вариантов ответа
    private String text; // Текст вопроса
    private String type; // Тип вопроса (OPEN, SINGLE_CHOICE, MULTIPLE_CHOICE)
}

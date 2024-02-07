package ru.gov.ac.isorv.option.dto;

import lombok.Data;

/**
 * DTO (Data Transfer Object) для создания нового варианта ответа.
 */
@Data
public class CreateOptionDto {
    private String text; // Текст варианта ответа
}

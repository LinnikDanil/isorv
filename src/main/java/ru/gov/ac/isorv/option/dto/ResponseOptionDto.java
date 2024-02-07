package ru.gov.ac.isorv.option.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO (Data Transfer Object) для представления варианта ответа.
 */
@Data
@Builder
public class ResponseOptionDto {
    private long id; // Идентификатор варианта ответа
    private String text; // Текст варианта ответа
}

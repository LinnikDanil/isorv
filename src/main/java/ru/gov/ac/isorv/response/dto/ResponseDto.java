package ru.gov.ac.isorv.response.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * DTO для передачи данных об ответах.
 * Может содержать список ответов в текстовой форме.
 */
@Data
@Builder
public class ResponseDto {
    List<String> responses; // Список ответов в текстовой форме
}

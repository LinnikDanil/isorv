package ru.gov.ac.isorv.poll.dto;

import lombok.Builder;
import lombok.Data;

/**
 * DTO для ответа с информацией об опросе.
 * Содержит базовую информацию об опросе, включая дату создания.
 */
@Data
@Builder
public class ResponsePollDto {
    Long id; // Идентификатор опроса
    String title; // Название опроса
    String createdDate; // Строковое представление даты создания опроса
}

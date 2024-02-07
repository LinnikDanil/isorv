package ru.gov.ac.isorv.response.service;

import ru.gov.ac.isorv.response.dto.CreateResponseDto;
import ru.gov.ac.isorv.response.dto.ResponseDto;

/**
 * Интерфейс сервиса для управления ответами на вопросы опроса.
 */
public interface ResponseService {
    /**
     * Создает ответ на вопрос опроса.
     *
     * @param questionId  Идентификатор вопроса.
     * @param responseDto DTO с данными ответа.
     * @return DTO сформированного ответа.
     */
    ResponseDto createResponse(Long questionId, CreateResponseDto responseDto);
}

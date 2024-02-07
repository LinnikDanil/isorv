package ru.gov.ac.isorv.option.service;

import ru.gov.ac.isorv.option.dto.CreateOptionDto;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;

import java.util.List;
import java.util.Set;

/**
 * Интерфейс сервиса для управления вариантами ответов.
 */
public interface OptionService {
    /**
     * Создает новый вариант ответа для заданного вопроса.
     *
     * @param questionId Идентификатор вопроса.
     * @param optionDto  DTO для создания варианта ответа.
     * @return DTO созданного варианта ответа.
     */
    ResponseOptionDto createOption(Long questionId, CreateOptionDto optionDto);

    /**
     * Возвращает список вариантов ответов по их идентификаторам.
     *
     * @param optionIds Набор идентификаторов вариантов ответа.
     * @return Список DTO вариантов ответа.
     */
    List<ResponseOptionDto> getOptionsByIds(Set<Long> optionIds);

    /**
     * Возвращает список вариантов ответов для заданного вопроса.
     *
     * @param questionId Идентификатор вопроса.
     * @return Список DTO вариантов ответа.
     */
    List<ResponseOptionDto> getOptionsByQuestionId(Long questionId);
}

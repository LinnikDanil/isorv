package ru.gov.ac.isorv.poll.service;

import ru.gov.ac.isorv.poll.dto.CreatePollDto;
import ru.gov.ac.isorv.poll.dto.ResponsePollDto;

import java.util.List;

/**
 * Интерфейс сервиса для управления опросами.
 * Определяет базовые операции создания, получения и извлечения опросов.
 */
public interface PollService {
    /**
     * Создает новый опрос на основе предоставленного DTO.
     *
     * @param pollDto DTO с данными для создания опроса.
     * @return DTO созданного опроса.
     */
    ResponsePollDto createPoll(CreatePollDto pollDto);

    /**
     * Возвращает опрос по его уникальному идентификатору.
     *
     * @param pollId Идентификатор опроса.
     * @return DTO запрашиваемого опроса.
     */
    ResponsePollDto getPollById(Long pollId);

    /**
     * Возвращает список всех опросов.
     *
     * @return Список DTO всех опросов.
     */
    List<ResponsePollDto> getPolls();
}

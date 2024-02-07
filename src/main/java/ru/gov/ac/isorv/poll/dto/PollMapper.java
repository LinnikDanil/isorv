package ru.gov.ac.isorv.poll.dto;

import lombok.experimental.UtilityClass;
import ru.gov.ac.isorv.poll.Poll;

import java.util.List;
import java.util.stream.Collectors;

import static ru.gov.ac.isorv.utils.TimeFormatConstants.TIMESTAMP_FORMATTER;

/**
 * Утилитный класс для преобразования между сущностью {@link Poll} и DTO {@link ResponsePollDto}.
 */
@UtilityClass
public class PollMapper {
    /**
     * Преобразует сущность Poll в DTO ResponsePollDto.
     *
     * @param poll Сущность опроса.
     * @return DTO представление опроса.
     */
    public static ResponsePollDto toPollDto(Poll poll) {
        return ResponsePollDto.builder()
                .id(poll.getId())
                .title(poll.getTitle())
                .createdDate(poll.getCreatedAt().format(TIMESTAMP_FORMATTER)) // Используется форматтер для форматирования даты создания
                .build();
    }

    /**
     * Преобразует список сущностей Poll в список DTO ResponsePollDto.
     *
     * @param polls Список сущностей опросов.
     * @return Список DTO представлений опросов.
     */
    public static List<ResponsePollDto> toPollDto(List<Poll> polls) {
        return polls.stream()
                .map(PollMapper::toPollDto)
                .collect(Collectors.toList());
    }
}
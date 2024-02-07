package ru.gov.ac.isorv.response.dto;

import lombok.experimental.UtilityClass;
import ru.gov.ac.isorv.option.Option;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Утилитный класс для преобразования сущностей {@link Option} в DTO {@link ResponseDto}.
 * Позволяет создать DTO из набора выбранных вариантов ответа или текста ответа.
 */
@UtilityClass
public class ResponseMapper {
    /**
     * Преобразует набор вариантов ответа в DTO.
     *
     * @param options Набор выбранных вариантов ответа.
     * @return DTO с текстами выбранных вариантов ответа.
     */
    public static ResponseDto toResponseDto(Set<Option> options) {
        return ResponseDto.builder()
                .responses(options.stream()
                        .map(Option::getText)
                        .collect(Collectors.toList()))
                .build();
    }

    /**
     * Создает DTO из текста ответа.
     *
     * @param text Текст ответа.
     * @return DTO, содержащий текст ответа.
     */
    public static ResponseDto toResponseDto(String text) {
        return ResponseDto.builder()
                .responses(List.of(text))
                .build();
    }
}

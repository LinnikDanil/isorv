package ru.gov.ac.isorv.option.dto;

import lombok.experimental.UtilityClass;
import ru.gov.ac.isorv.option.Option;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для маппинга между сущностями {@link Option} и DTO {@link ResponseOptionDto}.
 */
@UtilityClass
public class OptionMapper {
    /**
     * Преобразует сущность {@link Option} в DTO {@link ResponseOptionDto}.
     *
     * @param option Сущность варианта ответа.
     * @return DTO варианта ответа.
     */
    public static ResponseOptionDto toOptionDto(Option option) {
        return ResponseOptionDto.builder()
                .id(option.getId())
                .text(option.getText())
                .build();
    }

    /**
     * Преобразует список сущностей {@link Option} в список DTO {@link ResponseOptionDto}.
     *
     * @param options Список сущностей вариантов ответа.
     * @return Список DTO вариантов ответа.
     */
    public static List<ResponseOptionDto> toOptionDto(List<Option> options) {
        return options.stream()
                .map(OptionMapper::toOptionDto)
                .collect(Collectors.toList());
    }
}

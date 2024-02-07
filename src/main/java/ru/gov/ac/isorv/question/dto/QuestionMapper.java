package ru.gov.ac.isorv.question.dto;

import lombok.experimental.UtilityClass;
import ru.gov.ac.isorv.question.model.Question;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для маппинга между сущностью вопроса {@link Question} и его DTO {@link ResponseQuestionDto}.
 */
@UtilityClass
public class QuestionMapper {
    /**
     * Преобразует сущность вопроса в DTO.
     *
     * @param question Сущность вопроса.
     * @return DTO вопроса.
     */
    public static ResponseQuestionDto toQuestionDto(Question question) {
        return ResponseQuestionDto.builder()
                .id(question.getId())
                .order_index(question.getOrderIndex())
                .text(question.getText())
                .type(question.getType().toString())
                .build();
    }

    /**
     * Преобразует список сущностей вопросов в список их DTO.
     *
     * @param questions Список сущностей вопросов.
     * @return Список DTO вопросов.
     */
    public static List<ResponseQuestionDto> toQuestionDto(List<Question> questions) {
        return questions.stream()
                .map(QuestionMapper::toQuestionDto)
                .collect(Collectors.toList());
    }
}

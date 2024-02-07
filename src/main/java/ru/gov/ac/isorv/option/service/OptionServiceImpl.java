package ru.gov.ac.isorv.option.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.option.Option;
import ru.gov.ac.isorv.option.OptionRepository;
import ru.gov.ac.isorv.option.dto.CreateOptionDto;
import ru.gov.ac.isorv.option.dto.OptionMapper;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;
import ru.gov.ac.isorv.question.QuestionRepository;

import java.util.List;
import java.util.Set;

/**
 * Сервисная реализация для работы с вариантами ответов.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OptionServiceImpl implements OptionService {
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ResponseOptionDto createOption(Long questionId, CreateOptionDto optionDto) {
        return OptionMapper.toOptionDto(optionRepository.save(
                Option.builder()
                        .text(optionDto.getText())
                        .question(questionRepository.findById(questionId)
                                .orElseThrow(() -> new RuntimeException("Не найден вопрос с id = " + questionId)))
                        .build()));
    }

    /**
     * Возвращает список вариантов ответов по идентификаторам.
     *
     * @param optionIds Набор идентификаторов вариантов ответа.
     * @return Список DTO вариантов ответа.
     */
    public List<ResponseOptionDto> getOptionsByIds(Set<Long> optionIds) {
        return OptionMapper.toOptionDto(optionRepository.findAllById(optionIds));
    }

    @Override
    public List<ResponseOptionDto> getOptionsByQuestionId(Long questionId) {
        return OptionMapper.toOptionDto(optionRepository.findAllByQuestionId(questionId));
    }
}
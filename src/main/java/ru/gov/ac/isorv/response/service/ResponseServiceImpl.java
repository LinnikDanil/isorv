package ru.gov.ac.isorv.response.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.option.Option;
import ru.gov.ac.isorv.option.OptionRepository;
import ru.gov.ac.isorv.question.QuestionRepository;
import ru.gov.ac.isorv.question.model.Question;
import ru.gov.ac.isorv.question.model.QuestionType;
import ru.gov.ac.isorv.response.Response;
import ru.gov.ac.isorv.response.ResponseRepository;
import ru.gov.ac.isorv.response.dto.CreateResponseDto;
import ru.gov.ac.isorv.response.dto.ResponseDto;
import ru.gov.ac.isorv.response.dto.ResponseMapper;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация сервиса для управления ответами на вопросы опроса.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {
    private final ResponseRepository responseRepository;
    private final OptionRepository optionRepository;
    private final QuestionRepository questionRepository;

    @Override
    public ResponseDto createResponse(Long questionId, CreateResponseDto responseDto) {
        // Получение и валидация выбранных вариантов ответа и вопроса
        Set<Option> options = new HashSet<>(optionRepository.findAllById(responseDto.getSelectedOptionIds()));
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question with id = " + questionId + " does not exist."));

        // Создание и сохранение ответа
        Response response = responseRepository.save(Response.builder()
                .question(question)
                .text(responseDto.getText())
                .selectedOptions(options)
                .build());

        // Формирование DTO ответа в зависимости от типа вопроса
        if (question.getType().equals(QuestionType.OPEN)) {
            return ResponseMapper.toResponseDto(response.getText());
        }
        return ResponseMapper.toResponseDto(options);
    }
}

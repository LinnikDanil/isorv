package ru.gov.ac.isorv.question;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gov.ac.isorv.question.dto.CreateQuestionDto;
import ru.gov.ac.isorv.question.dto.ResponseQuestionDto;
import ru.gov.ac.isorv.question.service.QuestionService;

import java.util.List;

/**
 * Контроллер для управления вопросами через REST API.
 * Обрабатывает запросы на создание вопросов и получение списка вопросов для опроса.
 */
@RestController
@RequestMapping("/api/questions")
@Slf4j
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Создает новый вопрос для указанного опроса.
     *
     * @param questionDto DTO для создания вопроса.
     * @param pollId      Идентификатор опроса, к которому будет добавлен вопрос.
     */
    @PostMapping("/{pollId}")
    public void createQuestion(@RequestBody CreateQuestionDto questionDto, @PathVariable Long pollId) {
        log.info("Creating Question text = {}, type = {}, pollId = {}", questionDto.getText(), questionDto.getType(), pollId);
        questionService.createQuestion(pollId, questionDto);
    }

    /**
     * Возвращает список вопросов для указанного опроса.
     *
     * @param pollId Идентификатор опроса.
     * @return Список DTO вопросов.
     */
    @GetMapping("/{pollId}")
    public List<ResponseQuestionDto> getQuestions(@PathVariable Long pollId) {
        log.info("Getting Questions for pollId = {}", pollId);
        return questionService.getQuestionsByPollId(pollId);
    }
}

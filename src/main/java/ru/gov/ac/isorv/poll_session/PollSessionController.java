package ru.gov.ac.isorv.poll_session;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ru.gov.ac.isorv.poll_session.dto.PollSessionDto;
import ru.gov.ac.isorv.poll_session.dto.PollSessionShortDto;
import ru.gov.ac.isorv.poll_session.dto.PollSessionUserDto;
import ru.gov.ac.isorv.poll_session.service.PollSessionService;
import ru.gov.ac.isorv.question.dto.ResponseQuestionMainDto;
import ru.gov.ac.isorv.question.service.QuestionService;
import ru.gov.ac.isorv.response.dto.CreateResponseDto;
import ru.gov.ac.isorv.response.dto.ResponseDto;
import ru.gov.ac.isorv.response.service.ResponseService;

/**
 * Контроллер для управления сессиями опросов.
 * Предоставляет эндпоинты для создания сессий опроса, управления вопросами и обработки ответов.
 */
@RestController
@RequestMapping("/api/poll-session")
@Slf4j
@RequiredArgsConstructor
public class PollSessionController {
    private final PollSessionService pollSessionService;
    private final QuestionService questionService;
    private final ResponseService responseService;
    private final SimpMessagingTemplate messagingTemplate;

    /**
     * Создает новую сессию для указанного опроса.
     *
     * @param pollId Идентификатор опроса.
     * @return Краткие данные о созданной сессии.
     */
    @PostMapping("/create/{pollId}")
    public PollSessionShortDto createSession(@PathVariable Long pollId) {
        log.info("Poll Session Controller: startSession pollId = {}", pollId);

        PollSession pollSession = pollSessionService.createSession(pollId);

        return new PollSessionShortDto(pollSession.getId(), pollSession.getPoll().getTitle());
    }

    /**
     * Переключает на следующий вопрос в текущей сессии опроса.
     *
     * @param sessionId Идентификатор сессии опроса.
     * @return Данные о текущем состоянии сессии опроса.
     */
    @PutMapping("/{sessionId}/next-question")
    public PollSessionDto nextQuestion(@PathVariable Long sessionId) {
        log.info("Poll Session Controller: next-question sessionId = {}", sessionId);

        PollSession pollSession = pollSessionService.nextQuestion(sessionId);

        ResponseQuestionMainDto responseQuestionMainDto = questionService.getQuestionByOrderIndex(pollSession);
        PollSessionUserDto pollSessionUserDto = pollSessionService.getPollSession(pollSession);
        pollSessionUserDto.setOptions(responseQuestionMainDto.getOptions());

        messagingTemplate.convertAndSend("/topic/main/question/" + sessionId, responseQuestionMainDto); //ResponseMainDto

        messagingTemplate.convertAndSend("/topic/user/question/" + sessionId, pollSessionUserDto); //PollSessionUserDto

        return PollSessionDto.builder()
                .pollSessionId(pollSession.getId())
                .currentQuestionNumber(pollSession.getOrderIndex())
                .questionNumber(pollSession.getNumberOfQuestions())
                .build();
    }

    /**
     * Завершает сессию опроса.
     *
     * @param sessionId Идентификатор сессии опроса.
     * @return Обновленные данные о сессии опроса.
     */
    @PostMapping("/{sessionId}/complete-poll")
    public PollSession completePoll(@PathVariable Long sessionId) {
        log.info("Poll Session Controller: complete-poll sessionId = {}", sessionId);

        return pollSessionService.completePoll(sessionId);
    }

    /**
     * Создает ответ на текущий вопрос в сессии опроса.
     *
     * @param sessionId   Идентификатор сессии опроса.
     * @param questionId  Идентификатор вопроса.
     * @param responseDto DTO с данными ответа.
     * @return DTO созданного ответа.
     */
    @PostMapping("/{sessionId}/response/{questionId}")
    public ResponseDto CreateResponse(@PathVariable Long sessionId,
                                      @PathVariable Long questionId,
                                      @RequestBody CreateResponseDto responseDto) {
        log.info("Poll Session Controller: create Response text = {}, questionId = {}", responseDto.getText(), questionId);
        ResponseDto response = responseService.createResponse(questionId, responseDto);

        // Отправка ответа на главный экран через WebSocket
        messagingTemplate.convertAndSend("/topic/responses/" + sessionId, response.getResponses());

        return response;
    }
}
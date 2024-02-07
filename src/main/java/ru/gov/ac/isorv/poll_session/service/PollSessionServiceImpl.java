package ru.gov.ac.isorv.poll_session.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.poll.PollRepository;
import ru.gov.ac.isorv.poll_session.PollSession;
import ru.gov.ac.isorv.poll_session.PollSessionRepository;
import ru.gov.ac.isorv.poll_session.dto.PollSessionUserDto;
import ru.gov.ac.isorv.question.QuestionRepository;
import ru.gov.ac.isorv.question.model.Question;

import java.time.LocalDateTime;

/**
 * Реализация сервиса для управления сессиями опросов.
 * Обеспечивает логику создания сессии, перехода к следующему вопросу и завершения опроса.
 */
@Service
@RequiredArgsConstructor
public class PollSessionServiceImpl implements PollSessionService {
    private final PollSessionRepository pollSessionRepository;
    private final PollRepository pollRepository;
    private final QuestionRepository questionRepository;

    @Override
    public PollSession createSession(Long pollId) {
        return pollSessionRepository.save(
                PollSession.builder()
                        .poll(pollRepository.findById(pollId).orElseThrow(
                                () -> new RuntimeException("Не найден опрос с id = " + pollId)))
                        .numberOfQuestions(questionRepository.countAllByPollId(pollId))
                        .build()
        );
    }

    @Override
    public PollSession nextQuestion(Long sessionId) {
        PollSession pollSession = findPollSessionById(sessionId);

        int currentIndex = pollSession.getOrderIndex() + 1;
        int numberOfQuestions = pollSession.getNumberOfQuestions();
        if (currentIndex > numberOfQuestions) {
            throw new RuntimeException("Была попытка получения " + currentIndex + " вопроса, когда их всего " + numberOfQuestions);
        }

        pollSession.setOrderIndex(currentIndex);
        return pollSessionRepository.save(pollSession);
    }

    @Override
    public PollSession completePoll(Long sessionId) {
        PollSession pollSession = findPollSessionById(sessionId);

        pollSession.setCompleted(true);
        pollSession.setEndTime(LocalDateTime.now());

        return pollSessionRepository.save(pollSession);
    }

    @Override
    public PollSessionUserDto getPollSession(PollSession pollSession) {
        Question question = questionRepository.findByOrderIndexAndPollId(
                pollSession.getOrderIndex(),
                pollSession.getPoll().getId()
        ).orElseThrow(() -> new RuntimeException("Вопроса не существует"));

        return PollSessionUserDto.builder()
                .pollSessionId(pollSession.getId())
                .currentQuestionNumber(pollSession.getOrderIndex())
                .questionsNumber(pollSession.getNumberOfQuestions())
                .questionId(question.getId())
                .questionText(question.getText())
                .questionType(question.getType().toString())
                .build();
    }

    /**
     * Вспомогательный метод для поиска сессии опроса по идентификатору.
     *
     * @param sessionId Идентификатор сессии опроса.
     * @return Найденная сессия опроса.
     */
    private PollSession findPollSessionById(Long sessionId) {
        return pollSessionRepository.findById(sessionId).orElseThrow(
                () -> new RuntimeException("Не найдена сессия с id = " + sessionId));
    }
}
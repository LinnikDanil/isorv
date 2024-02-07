package ru.gov.ac.isorv.poll_session.service;

import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.poll_session.PollSession;
import ru.gov.ac.isorv.poll_session.dto.PollSessionUserDto;

/**
 * Сервис для управления сессиями опросов.
 * Предоставляет методы для создания сессии, перехода к следующему вопросу,
 * завершения опроса и получения информации о текущей сессии.
 */
@Service
public interface PollSessionService {
    /**
     * Создает новую сессию для заданного опроса.
     *
     * @param pollId Идентификатор опроса.
     * @return Созданная сессия опроса.
     */
    PollSession createSession(Long pollId);

    /**
     * Переходит к следующему вопросу в текущей сессии опроса.
     *
     * @param sessionId Идентификатор сессии опроса.
     * @return Обновленная сессия опроса с информацией о текущем вопросе.
     */
    PollSession nextQuestion(Long sessionId);

    /**
     * Завершает сессию опроса.
     *
     * @param sessionId Идентификатор сессии опроса.
     * @return Обновленная сессия опроса с информацией о завершении.
     */
    PollSession completePoll(Long sessionId);

    /**
     * Получает данные о текущей сессии опроса для пользователя.
     *
     * @param pollSession Текущая сессия опроса.
     * @return DTO с данными сессии для пользователя.
     */
    PollSessionUserDto getPollSession(PollSession pollSession);
}

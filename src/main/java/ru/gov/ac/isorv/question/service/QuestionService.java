package ru.gov.ac.isorv.question.service;

import ru.gov.ac.isorv.poll_session.PollSession;
import ru.gov.ac.isorv.question.dto.CreateQuestionDto;
import ru.gov.ac.isorv.question.dto.ResponseQuestionDto;
import ru.gov.ac.isorv.question.dto.ResponseQuestionMainDto;

import java.util.List;

/**
 * Интерфейс сервиса для управления вопросами в опросах.
 * Определяет методы для создания вопросов, получения вопросов по индексу порядка и ID опроса.
 */
public interface QuestionService {
    /**
     * Создает новый вопрос в определенном опросе.
     *
     * @param pollId      Идентификатор опроса, к которому будет принадлежать вопрос.
     * @param questionDto DTO с данными для создания вопроса.
     */
    void createQuestion(Long pollId, CreateQuestionDto questionDto);

    /**
     * Возвращает данные текущего вопроса в сессии опроса, включая информацию для отображения на главном экране.
     *
     * @param pollSession Текущая сессия опроса.
     * @return DTO с информацией о текущем вопросе для главного экрана.
     */
    ResponseQuestionMainDto getQuestionByOrderIndex(PollSession pollSession);

    /**
     * Возвращает список всех вопросов, связанных с определенным опросом.
     *
     * @param pollId Идентификатор опроса.
     * @return Список DTO вопросов.
     */
    List<ResponseQuestionDto> getQuestionsByPollId(Long pollId);
}

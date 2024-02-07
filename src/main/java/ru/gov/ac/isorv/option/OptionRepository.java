package ru.gov.ac.isorv.option;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для управления сущностями {@link Option} в базе данных.
 */
public interface OptionRepository extends JpaRepository<Option, Long> {
    /**
     * Находит все варианты ответов, ассоциированные с заданным вопросом.
     *
     * @param questionId Идентификатор вопроса.
     * @return Список вариантов ответов для вопроса.
     */
    List<Option> findAllByQuestionId(Long questionId);
}

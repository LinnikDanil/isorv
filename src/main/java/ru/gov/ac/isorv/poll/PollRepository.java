package ru.gov.ac.isorv.poll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий JPA для сущности {@link Poll}.
 * Предоставляет методы для взаимодействия с таблицей опросов в базе данных.
 */
public interface PollRepository extends JpaRepository<Poll, Long> {
}
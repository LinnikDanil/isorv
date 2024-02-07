package ru.gov.ac.isorv.poll_session;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий JPA для сущности {@link PollSession}.
 * Предоставляет стандартные методы для работы с сессиями опросов в базе данных.
 */
public interface PollSessionRepository extends JpaRepository<PollSession, Long> {
}

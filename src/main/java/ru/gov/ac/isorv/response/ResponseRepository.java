package ru.gov.ac.isorv.response;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий JPA для управления сущностями {@link Response} в базе данных.
 */
public interface ResponseRepository extends JpaRepository<Response, Long> {
}

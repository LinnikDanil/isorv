package ru.gov.ac.isorv.poll_session;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.gov.ac.isorv.poll.Poll;

import java.time.LocalDateTime;

/**
 * Сущность, представляющая сессию опроса.
 * Хранит информацию о связанном опросе, времени начала и окончания сессии,
 * статусе завершенности, текущем вопросе и общем количестве вопросов.
 */
@Entity
@Table(name = "poll_sessions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PollSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор сессии

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "poll_id")
    private Poll poll; // Связанный опрос

    @Column(nullable = false)
    private LocalDateTime startTime; // Время начала сессии

    @Column()
    private LocalDateTime endTime; // Время окончания сессии

    @Column(nullable = false)
    private boolean isCompleted; // Статус завершенности сессии

    @Column(nullable = false)
    private Integer orderIndex; // Порядковый номер текущего вопроса

    @Column(nullable = false)
    private Integer numberOfQuestions; // Общее количество вопросов в опросе

    @PrePersist
    protected void onCreate() {
        startTime = LocalDateTime.now(); // Установка времени начала при создании сессии
        isCompleted = false; // Инициализация статуса завершенности как "не завершено"
        orderIndex = 0; // Начальное значение порядкового номера вопроса
    }
}

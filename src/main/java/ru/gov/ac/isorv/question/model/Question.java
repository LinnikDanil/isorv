package ru.gov.ac.isorv.question.model;

import jakarta.persistence.*;
import lombok.*;
import ru.gov.ac.isorv.poll.Poll;

import java.time.LocalDateTime;

/**
 * Сущность, представляющая вопрос в опросе.
 * Содержит информацию о тексте вопроса, его типе, порядковом номере,
 * а также ссылку на опрос, к которому относится вопрос.
 */
@Entity
@Table(name = "questions")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор вопроса

    @Column(nullable = false)
    private Integer orderIndex; // Порядковый номер вопроса в опросе

    @Column(nullable = false)
    private String text; // Текст вопроса

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poll_id")
    private Poll poll; // Опрос, к которому относится вопрос

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuestionType type; // Тип вопроса

    @Column(nullable = false)
    private LocalDateTime createdAt; // Время создания вопроса

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Автоматически устанавливается время создания
    }
}

package ru.gov.ac.isorv.response;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.gov.ac.isorv.option.Option;
import ru.gov.ac.isorv.poll_session.PollSession;
import ru.gov.ac.isorv.question.model.Question;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Сущность, представляющая ответ пользователя на вопрос опроса.
 */
@Entity
@Table(name = "responses")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Идентификатор ответа

    @Column(nullable = true)
    private String text; // Текст ответа (для открытых вопросов)

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id")
    private Question question; // Вопрос, на который дается ответ

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "poll_session_id")
    private PollSession pollSession; // Сессия опроса, в рамках которой был дан ответ

    @Column(nullable = false)
    private LocalDateTime timestamp; // Время создания ответа

    @ManyToMany
    @JoinTable(
            name = "selected_option",
            joinColumns = @JoinColumn(name = "response_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id")
    )
    private Set<Option> selectedOptions = new HashSet<>(); // Выбранные варианты ответа

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now(); // Автоматическая установка времени создания ответа
    }
}

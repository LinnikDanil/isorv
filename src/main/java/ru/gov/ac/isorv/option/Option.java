package ru.gov.ac.isorv.option;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.gov.ac.isorv.question.model.Question;

/**
 * Сущность, представляющая вариант ответа в опросе.
 */
@Entity
@Table(name = "options")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор варианта ответа

    @Column(nullable = false)
    private String text; // Текст варианта ответа

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "question_id")
    private Question question; // Вопрос, к которому относится вариант ответа
}

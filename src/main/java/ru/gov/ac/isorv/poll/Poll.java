package ru.gov.ac.isorv.poll;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Сущность, представляющая опрос.
 * Содержит информацию о названии опроса и времени его создания.
 */
@Entity
@Table(name = "polls")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Уникальный идентификатор опроса

    @Column(nullable = false)
    private String title; // Название опроса

    @Column(nullable = false)
    private LocalDateTime createdAt; // Время создания опроса

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now(); // Автоматическое установление времени создания
    }
}

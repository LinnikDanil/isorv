package ru.gov.ac.isorv.poll.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для создания нового опроса.
 * Содержит поля, необходимые для создания опроса.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePollDto {
    @NotBlank
    @Size(min = 1, max = 100)
    private String title; // Название опроса
}

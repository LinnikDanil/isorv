package ru.gov.ac.isorv.option;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gov.ac.isorv.option.dto.CreateOptionDto;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;
import ru.gov.ac.isorv.option.service.OptionService;

import java.util.List;

/**
 * REST-контроллер для управления вариантами ответов.
 */
@RestController
@RequestMapping("/api/options")
@Slf4j
@RequiredArgsConstructor
public class OptionController {
    private final OptionService optionService;

    /**
     * Создает новый вариант ответа для заданного вопроса.
     *
     * @param questionId Идентификатор вопроса.
     * @param optionDto  DTO для создания варианта ответа.
     * @return DTO созданного варианта ответа.
     */
    @PostMapping("/{questionId}")
    public ResponseOptionDto createOption(@PathVariable Long questionId, @RequestBody CreateOptionDto optionDto) {
        log.info("Option Controller: create Option text = {}, questionId = {}", optionDto.getText(), questionId);
        return optionService.createOption(questionId, optionDto);
    }

    /**
     * Возвращает список вариантов ответов для заданного вопроса.
     *
     * @param questionId Идентификатор вопроса.
     * @return Список DTO вариантов ответа.
     */
    @GetMapping("/{questionId}")
    public List<ResponseOptionDto> getOptions(@PathVariable Long questionId) {
        log.info("Option Controller: get Options questionId = {}", questionId);
        return optionService.getOptionsByQuestionId(questionId);
    }
}

package ru.gov.ac.isorv.poll;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.gov.ac.isorv.poll.dto.CreatePollDto;
import ru.gov.ac.isorv.poll.dto.ResponsePollDto;
import ru.gov.ac.isorv.poll.service.PollService;

import java.util.List;

/**
 * REST контроллер для управления опросами.
 * Обрабатывает HTTP запросы для создания и получения информации об опросах.
 */
@RestController
@RequestMapping("/api/polls")
@Slf4j
@RequiredArgsConstructor
public class PollController {
    private final PollService pollService;

    /**
     * Создает новый опрос.
     *
     * @param pollDto DTO с данными для создания опроса.
     * @return DTO созданного опроса.
     */
    @PostMapping
    public ResponsePollDto createPoll(@RequestBody CreatePollDto pollDto) {
        log.info("Creating Poll with title = {}", pollDto.getTitle());
        return pollService.createPoll(pollDto);
    }

    /**
     * Возвращает опрос по его идентификатору.
     *
     * @param pollId Идентификатор опроса.
     * @return DTO запрашиваемого опроса.
     */
    @GetMapping("/{pollId}")
    public ResponsePollDto getPoll(@PathVariable Long pollId) {
        log.info("Getting Poll by id = {}", pollId);
        return pollService.getPollById(pollId);
    }

    /**
     * Возвращает список всех доступных опросов.
     *
     * @return Список DTO всех опросов.
     */
    @GetMapping
    public List<ResponsePollDto> getAllPolls() {
        log.info("Getting all polls");
        return pollService.getPolls();
    }
}
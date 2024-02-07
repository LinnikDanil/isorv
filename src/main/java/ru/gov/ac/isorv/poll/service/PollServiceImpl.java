package ru.gov.ac.isorv.poll.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.poll.Poll;
import ru.gov.ac.isorv.poll.PollRepository;
import ru.gov.ac.isorv.poll.dto.CreatePollDto;
import ru.gov.ac.isorv.poll.dto.PollMapper;
import ru.gov.ac.isorv.poll.dto.ResponsePollDto;

import java.util.List;

/**
 * Сервисная реализация для работы с опросами
 */
@Service
@RequiredArgsConstructor
public class PollServiceImpl implements PollService {
    private final PollRepository pollRepository;

    @Override
    public ResponsePollDto createPoll(CreatePollDto pollDto) {
        return PollMapper.toPollDto(pollRepository.save(Poll.builder().title(pollDto.getTitle()).build()));
    }

    @Override
    public ResponsePollDto getPollById(Long pollId) {
        return PollMapper.toPollDto(pollRepository.findById(pollId).orElseThrow(
                () -> new RuntimeException("Опроса с id = " + pollId + " не существует.")));
    }

    @Override
    public List<ResponsePollDto> getPolls() {
        return PollMapper.toPollDto(pollRepository.findAll());
    }
}

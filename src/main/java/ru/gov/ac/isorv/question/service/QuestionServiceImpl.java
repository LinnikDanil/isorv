package ru.gov.ac.isorv.question.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.gov.ac.isorv.option.Option;
import ru.gov.ac.isorv.option.OptionRepository;
import ru.gov.ac.isorv.option.dto.OptionMapper;
import ru.gov.ac.isorv.option.dto.ResponseOptionDto;
import ru.gov.ac.isorv.poll.PollRepository;
import ru.gov.ac.isorv.poll_session.PollSession;
import ru.gov.ac.isorv.question.QuestionRepository;
import ru.gov.ac.isorv.question.dto.CreateQuestionDto;
import ru.gov.ac.isorv.question.dto.QuestionMapper;
import ru.gov.ac.isorv.question.dto.ResponseQuestionDto;
import ru.gov.ac.isorv.question.dto.ResponseQuestionMainDto;
import ru.gov.ac.isorv.question.model.Question;
import ru.gov.ac.isorv.question.model.QuestionType;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Реализация сервиса для управления вопросами.
 * Отвечает за создание вопросов и извлечение информации о вопросах из базы данных.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;

    @Override
    public void createQuestion(Long pollId, CreateQuestionDto questionDto) {
        int questionIndex = questionRepository.countAllByPollId(pollId);

        Question question = questionRepository.save(
                Question.builder()
                        .text(questionDto.getText())
                        .orderIndex(++questionIndex)
                        .type(QuestionType.valueOf(questionDto.getType()))
                        .poll(pollRepository.findById(pollId).orElseThrow(
                                () -> new RuntimeException("Не найден опрос с id = " + pollId)))
                        .build());
        log.info("QUESTION SAVED: " + question);

        List<Option> options = optionRepository.saveAll(questionDto.getOptions().stream()
                .map(option -> Option.builder()
                        .question(question)
                        .text(option)
                        .build())
                .collect(Collectors.toList()));
        log.info("OPTIONS SAVED: " + options);
    }

    @Override
    public ResponseQuestionMainDto getQuestionByOrderIndex(PollSession pollSession) {
        Question question = questionRepository.findByOrderIndexAndPollId(
                pollSession.getOrderIndex(),
                pollSession.getPoll().getId()
        ).orElseThrow(() -> new RuntimeException("Вопроса не существует"));

        List<ResponseOptionDto> optionsDto = OptionMapper.toOptionDto(
                optionRepository.findAllByQuestionId(question.getId()));

        return ResponseQuestionMainDto.builder()
                .currentQuestionNumber(pollSession.getOrderIndex())
                .questionsNumber(pollSession.getNumberOfQuestions())
                .questionType(question.getType().toString())
                .questionText(question.getText())
                .options(optionsDto)
                .build();
    }

    @Override
    public List<ResponseQuestionDto> getQuestionsByPollId(Long pollId) {
        return QuestionMapper.toQuestionDto(questionRepository.findAllByPollId(pollId));
    }
}
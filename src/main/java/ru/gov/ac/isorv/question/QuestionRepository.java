package ru.gov.ac.isorv.question;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gov.ac.isorv.question.model.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Integer countAllByPollId(long pollId);

    Optional<Question> findByOrderIndexAndPollId(int orderIndex, long pollId);

    List<Question> findAllByPollId(long pollId);
}

package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Answer;

import java.util.Optional;

public interface AnswerRepository {

    Answer save(Answer answer);

    Optional<Answer> findById(Integer id);
}

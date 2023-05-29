package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Question;

public interface QuestionRepository {

    Question save(Question question);
}

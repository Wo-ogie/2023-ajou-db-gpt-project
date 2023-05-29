package ajou.db.gpt.repository;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.constant.SortCond;
import ajou.db.gpt.domain.Question;
import ajou.db.gpt.dto.chat.QuestionWithAnswerRes;

import java.util.List;

public interface QuestionRepository {

    Question save(Question question);

    List<QuestionWithAnswerRes> searchQnAs(String userId, Category category, String keyword, SortCond sort);
}

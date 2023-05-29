package ajou.db.gpt.service;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.constant.SortCond;
import ajou.db.gpt.domain.Question;
import ajou.db.gpt.dto.chat.QuestionWithAnswerRes;
import ajou.db.gpt.exception.question.QuestionNotFoundByIdException;
import ajou.db.gpt.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Transactional
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question findById(Integer questionId) {
        return questionRepository.findById(questionId).orElseThrow(() -> new QuestionNotFoundByIdException(questionId));
    }

    public List<QuestionWithAnswerRes> searchQnAs(String userId, Category category, String keyword, SortCond sort) {
        return questionRepository.searchQnAs(userId, category, keyword, sort);
    }
}

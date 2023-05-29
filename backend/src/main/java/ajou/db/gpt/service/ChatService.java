package ajou.db.gpt.service;

import ajou.db.gpt.config.OpenAIService;
import ajou.db.gpt.constant.Category;
import ajou.db.gpt.domain.Answer;
import ajou.db.gpt.domain.Question;
import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.chat.ChatRes;
import ajou.db.gpt.dto.chat.GptRes;
import ajou.db.gpt.repository.AnswerRepository;
import ajou.db.gpt.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ChatService {

    private final UserService userService;
    private final OpenAIService openaiService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    @Transactional
    public ChatRes chat(String userId, Category category, String content) {
        GptRes chatRes = openaiService.chat(content);

        User user = userService.findById(userId);
        Answer answer = answerRepository.save(new Answer(chatRes.getAnswer()));
        Question question = questionRepository.save(new Question(user, answer, category, content));

        return new ChatRes(question.getContent(), answer.getContent());
    }
}

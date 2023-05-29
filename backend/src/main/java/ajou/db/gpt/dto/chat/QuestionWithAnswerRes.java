package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionWithAnswerRes {

    private QuestionRes question;
    private String answer;

    public QuestionWithAnswerRes(Category category, String content, String answer) {
        this.question = new QuestionRes(category, content);
        this.answer = answer;
    }
}

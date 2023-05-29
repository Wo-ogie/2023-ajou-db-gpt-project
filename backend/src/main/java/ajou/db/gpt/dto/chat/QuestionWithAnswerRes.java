package ajou.db.gpt.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionWithAnswerRes {

    private QuestionWithMarkedStatusRes question;
    private String answer;
}

package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionRes {

    private Category category;
    private String content;
}

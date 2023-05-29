package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionRes {

    private Integer id;
    private Category category;
    private String content;

    public static QuestionRes from(Question entity) {
        return new QuestionRes(entity.getId(), entity.getCategory(), entity.getContent());
    }
}

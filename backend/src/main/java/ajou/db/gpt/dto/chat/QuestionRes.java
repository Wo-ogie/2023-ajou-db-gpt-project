package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.domain.Question;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionRes {

    @Schema(description = "PK of question", example = "3")
    private Integer id;

    private Category category;

    @Schema(description = "질문 내용", example = "1+1이 뭐야?")
    private String content;

    public static QuestionRes from(Question entity) {
        return new QuestionRes(entity.getId(), entity.getCategory(), entity.getContent());
    }
}

package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionWithMarkedStatusRes {

    @Schema(description = "PK of question")
    private Integer id;

    private Category category;

    @Schema(description = "질문 내용", example = "1+1이 뭐야?")
    private String content;

    @Schema(description = "북마크 저장 여부", example = "false")
    private Boolean isMarked;
}

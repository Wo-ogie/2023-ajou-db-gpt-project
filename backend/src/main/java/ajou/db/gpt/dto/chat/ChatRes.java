package ajou.db.gpt.dto.chat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ChatRes {

    @Schema(description = "질문")
    private QuestionRes question;

    @Schema(description = "답변", example = "1+1은 2야.")
    private String answer;
}

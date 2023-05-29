package ajou.db.gpt.dto.chat;

import ajou.db.gpt.constant.Category;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ChatReq {

    @NotNull
    private Category category;

    @Schema(description = "질문 내용", example = "1+1이 뭐야?")
    @NotEmpty
    private String content;
}

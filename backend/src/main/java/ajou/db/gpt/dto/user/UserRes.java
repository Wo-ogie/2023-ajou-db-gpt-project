package ajou.db.gpt.dto.user;

import ajou.db.gpt.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRes {

    @Schema(description = "유저 id", example = "test")
    @NotEmpty
    private String id;

    @Schema(description = "이름", example = "테스트유저")
    @NotEmpty
    private String name;

    public static UserRes from(User entity) {
        return new UserRes(entity.getId(), entity.getName());
    }
}

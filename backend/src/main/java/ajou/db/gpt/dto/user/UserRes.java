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

    @Schema(description = "유저 pw", example = "1234")
    @NotEmpty
    private String password;

    public static UserRes from(User entity) {
        return new UserRes(entity.getId(), entity.getName());
    }
}

package ajou.db.gpt.dto.user;

import ajou.db.gpt.domain.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class UserCreateReq {

    @Schema(description = "유저 id", example = "test")
    @NotEmpty
    private String id;

    @Schema(description = "유저 pw", example = "1234")
    @NotEmpty
    private String password;

    @Schema(description = "이름", example = "테스트유저")
    @NotEmpty
    private String name;

    public User toEntity(String encodedPassword) {
        return new User(this.id, encodedPassword, this.name);
    }
}

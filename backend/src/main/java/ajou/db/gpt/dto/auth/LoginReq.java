package ajou.db.gpt.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginReq {

    @Schema(description = "ID", example = "test")
    @NotEmpty
    private String id;

    @Schema(description = "PW", example = "1234")
    @NotEmpty
    private String password;
}

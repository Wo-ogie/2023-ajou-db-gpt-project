package ajou.db.gpt.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class TokenRefreshRequest {

    @Schema(description = "기존 발급받은 refresh token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IlJPTEVfVVNFUiIsImxvZ2luVHlwZSI6IktBS0FPIiwiaWF0IjoxNjc3NTc1MTgwLCJleHAiOjE2ODAxNjcxODB9.SaUn_nlZxKiWhwm2GxGCJeC3t9XU7Gl1dLdjPc_mBFo")
    @NotBlank
    private String refreshToken;
}

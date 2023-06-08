package ajou.db.gpt.controller;

import ajou.db.gpt.dto.auth.LoginReq;
import ajou.db.gpt.dto.auth.LoginRes;
import ajou.db.gpt.dto.auth.request.TokenRefreshRequest;
import ajou.db.gpt.dto.auth.response.TokenResponse;
import ajou.db.gpt.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "로그인",
            description = "<p>아이디와 비밀번호를 받아 로그인을 수행합니다." +
                    "<p>로그인에 성공하면 로그인 사용자 정보, access token을 응답합니다. 이후 로그인 권한이 필요한 API를 호출할 때는 HTTP header의 <strong>Authorization</strong>에 access token을 담아서 요청해야 합니다." +
                    "<p>Access token의 만료기한은 일주일입니다."
    )
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = LoginRes.class))),
            @ApiResponse(description = "<p>[2000] If id is invalid" +
                    "<p>[1503] If password is invalid",
                    responseCode = "401", content = @Content),
    })
    @PostMapping("/login")
    public LoginRes login(@Valid @RequestBody LoginReq req) {
        return authService.login(req.getId(), req.getPassword());
    }

    @Operation(
            summary = "토큰 갱신하기",
            description = "<p>기존 발급받은 refresh token으로 새로운 access token과 refresh token을 발급 받습니다."
    )
    @ApiResponses({
            @ApiResponse(description = "OK", responseCode = "200", content = @Content(schema = @Schema(implementation = TokenResponse.class))),
            @ApiResponse(description = "[1502] 유효하지 않은 token으로 요청한 경우. Token 값이 잘못되었거나 만료되어 유효하지 않은 경우로 token 갱신 필요", responseCode = "401", content = @Content),
    })
    @PostMapping("/tokens")
    public TokenResponse tokenRefresh(@Valid @RequestBody TokenRefreshRequest request) {
        return authService.refresh(request.getRefreshToken());
    }
}

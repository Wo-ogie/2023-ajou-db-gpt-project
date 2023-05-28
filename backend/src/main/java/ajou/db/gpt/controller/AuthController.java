package ajou.db.gpt.controller;

import ajou.db.gpt.dto.auth.LoginReq;
import ajou.db.gpt.dto.auth.LoginRes;
import ajou.db.gpt.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "인증")
@RequiredArgsConstructor
@RequestMapping("/auth")
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
            @ApiResponse(description = "[2000] If id is invalid", responseCode = "401", content = @Content),
            @ApiResponse(description = "[1503] If password is invalid", responseCode = "401", content = @Content)
    })
    @PostMapping("/login")
    public LoginRes login(@Valid @RequestBody LoginReq req) {
        return authService.login(req.getId(), req.getPassword());
    }
}

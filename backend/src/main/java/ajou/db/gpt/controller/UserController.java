package ajou.db.gpt.controller;

import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.user.UserCreateReq;
import ajou.db.gpt.dto.user.UserRes;
import ajou.db.gpt.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "유저(회원)")
@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "회원가입",
            description = "회원가입에 필요한 정보(아이디, 비밀번호, 이름)을 받아 회원가입을 수행한다."
    )
    @PostMapping
    public ResponseEntity<UserRes> createUser(@Valid @RequestBody UserCreateReq userReq) {
        User user = userService.save(userReq);
        return ResponseEntity
                .created(URI.create("/api/users/" + user.getId()))
                .body(UserRes.from(user));
    }
}

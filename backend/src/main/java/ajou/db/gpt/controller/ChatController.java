package ajou.db.gpt.controller;

import ajou.db.gpt.dto.chat.ChatReq;
import ajou.db.gpt.dto.chat.ChatRes;
import ajou.db.gpt.security.UserPrincipal;
import ajou.db.gpt.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "채팅")
@RequiredArgsConstructor
@RequestMapping("/api/chat")
@RestController
public class ChatController {

    private final ChatService chatService;

    @Operation(
            summary = "채팅",
            description = "GPT model에게 궁금한 것을 질문한다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping
    public ChatRes chat(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Valid @RequestBody ChatReq req
    ) {
        return chatService.chat(userPrincipal.getUsername(), req.getCategory(), req.getContent());
    }
}

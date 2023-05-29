package ajou.db.gpt.controller;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.constant.SortCond;
import ajou.db.gpt.dto.chat.ChatReq;
import ajou.db.gpt.dto.chat.ChatRes;
import ajou.db.gpt.security.UserPrincipal;
import ajou.db.gpt.service.ChatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "채팅")
@RequiredArgsConstructor
@RequestMapping("/api/chats")
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

    @Operation(
            summary = "내 질문기록 조회",
            description = "내 질문기록 조회",
            security = @SecurityRequirement(name = "access-token")
    )
    @GetMapping
    public ChatListRes searchQnAs(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @Parameter(description = "필터링할 카테고리") @RequestParam(required = false) Category category,
            @Parameter(description = "검색 키워드", example = "Java") @RequestParam(required = false) String keyword,
            @Parameter(description = "정렬 조건") @RequestParam(required = false, defaultValue = "LATEST") SortCond sort
    ) {
        return new ChatListRes(chatService.searchQnAs(userPrincipal.getUsername(), category, keyword, sort));
    }
}

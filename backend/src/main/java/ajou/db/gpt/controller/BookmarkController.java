package ajou.db.gpt.controller;

import ajou.db.gpt.domain.Bookmark;
import ajou.db.gpt.dto.bookmark.BookmarkRes;
import ajou.db.gpt.security.UserPrincipal;
import ajou.db.gpt.service.BookmarkService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "북마크")
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
@RestController
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Operation(
            summary = "질문 저장",
            description = "질문을 북마크에 저장한다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @PostMapping("/questions/{questionId}")
    public ResponseEntity<BookmarkRes> markQuestion(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Integer questionId
    ) {
        Bookmark bookmark = bookmarkService.save(userPrincipal.getUsername(), questionId);
        return ResponseEntity
                .created(URI.create("/api/bookmarks/" + bookmark.getId()))
                .body(BookmarkRes.from(bookmark));
    }

    @Operation(
            summary = "질문 북마크 취소",
            description = "저장했던 질문을 북마크에서 삭제한다.",
            security = @SecurityRequirement(name = "access-token")
    )
    @DeleteMapping("/questions/{questionId}")
    public void cancelQuestionBookmark(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Integer questionId
    ) {
        bookmarkService.delete(userPrincipal.getUsername(), questionId);
    }
}

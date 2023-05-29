package ajou.db.gpt.dto.bookmark;

import ajou.db.gpt.domain.Bookmark;
import ajou.db.gpt.dto.chat.QuestionRes;
import ajou.db.gpt.dto.user.UserRes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class BookmarkRes {

    private Integer id;
    private UserRes userRes;
    private QuestionRes questionRes;

    public static BookmarkRes from(Bookmark entity) {
        return new BookmarkRes(
                entity.getId(),
                UserRes.from(entity.getUser()),
                QuestionRes.from(entity.getQuestion())
        );
    }
}

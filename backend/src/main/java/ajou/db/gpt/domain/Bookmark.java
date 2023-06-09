package ajou.db.gpt.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Bookmark extends BaseTimeEntity {

    private Integer id;
    private User user;
    private Question question;

    public Bookmark(User user, Question question) {
        this(null, user, question, null);
    }

    public Bookmark(Integer id, User user, Question question, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.user = user;
        this.question = question;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package ajou.db.gpt.domain;

import ajou.db.gpt.constant.Category;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Question extends BaseTimeEntity {

    private Integer id;
    private User user;
    private Answer answer;
    private Category category;
    private String content;

    public Question(User user, Answer answer, Category category, String content) {
        this(null, user, answer, category, content, null);
    }

    public Question(Integer id, User user, Answer answer, Category category, String content, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.user = user;
        this.answer = answer;
        this.category = category;
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

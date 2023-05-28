package ajou.db.gpt.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Question extends BaseTimeEntity {

    private Integer id;
    private User user;
    private Answer answer;
    private String category;
    private String content;

    public Question(Integer id, User user, Answer answer, String category, String content, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.user = user;
        this.answer = answer;
        this.category = category;
        this.content = content;
    }
}

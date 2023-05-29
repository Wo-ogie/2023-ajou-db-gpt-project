package ajou.db.gpt.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Answer extends BaseTimeEntity {

    private Integer id;
    private String content;

    public Answer(String content) {
        this(null, content, null);
    }

    public Answer(Integer id, String content, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.content = content;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package ajou.db.gpt.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User extends BaseTimeEntity {

    private String id;
    private String password;
    private String name;

    public User(String id, String password, String name) {
        this(id, password, name, null);
    }

    public User(String id, String password, String name, LocalDateTime createdAt) {
        super(createdAt);
        this.id = id;
        this.password = password;
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}

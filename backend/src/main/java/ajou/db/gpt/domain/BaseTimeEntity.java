package ajou.db.gpt.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BaseTimeEntity {

    protected LocalDateTime createdAt;
}

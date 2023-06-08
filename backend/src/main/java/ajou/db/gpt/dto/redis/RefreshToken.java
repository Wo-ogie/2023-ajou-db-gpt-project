package ajou.db.gpt.dto.redis;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@RedisHash(value = "refreshToken", timeToLive = 30 * 24 * 60 * 60)
public class RefreshToken {

    @NotNull
    @Id
    private String token;

    @NotNull
    private String userId;

    public static RefreshToken of(String token, String userId) {
        return new RefreshToken(token, userId);
    }
}

package ajou.db.gpt.security;

import java.time.LocalDateTime;

public record JwtTokenInfoDto (
        String token,
        LocalDateTime expiresAt
) {
}

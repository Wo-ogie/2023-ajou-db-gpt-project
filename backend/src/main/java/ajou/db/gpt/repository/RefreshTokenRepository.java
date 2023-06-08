package ajou.db.gpt.repository;

import ajou.db.gpt.dto.redis.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}

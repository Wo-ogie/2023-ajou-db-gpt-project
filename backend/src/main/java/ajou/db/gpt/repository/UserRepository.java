package ajou.db.gpt.repository;

import ajou.db.gpt.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findById(String id);
}

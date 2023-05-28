package ajou.db.gpt.repository;

import ajou.db.gpt.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final NamedParameterJdbcTemplate template;

    public UserRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public User save(User user) {
        LocalDateTime now = LocalDateTime.now();
        String sql = "INSERT INTO users (user_id, password, name, created_at) " +
                "VALUES (:user_id, :password, :name, :created_at)";

        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", user.getId())
                .addValue("password", user.getPassword())
                .addValue("name", user.getName())
                .addValue("created_at", now);
        template.update(sql, param);

        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        String sql = "select user_id, password, name, created_at " +
                "from users " +
                "where user_id=:id";

        Map<String, Object> param = Map.of("id", id);
        User user = template.queryForObject(sql, param, userRowMapper());
        return user != null ? Optional.of(user) : Optional.empty();
    }

    private RowMapper<User> userRowMapper() {
        return (rs, rowNum) -> new User(
                rs.getString("user_id"),
                rs.getString("password"),
                rs.getString("name"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}

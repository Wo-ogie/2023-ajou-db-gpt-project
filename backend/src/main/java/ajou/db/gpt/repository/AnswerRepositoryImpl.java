package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Answer;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AnswerRepositoryImpl implements AnswerRepository {

    private final NamedParameterJdbcTemplate template;

    public AnswerRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Answer save(Answer answer) {
        String sql = "INSERT INTO answer (content, created_at) " +
                "VALUES (:content, :created_at)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("content", answer.getContent())
                .addValue("created_at", LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);

        int key = Objects.requireNonNull(keyHolder.getKey()).intValue();
        answer.setId(key);
        return answer;
    }

    @Override
    public Optional<Answer> findById(Integer id) {
        String sql = "SELECT answer_id, content, created_at " +
                "FROM answer " +
                "WHERE answer_id=:id";
        Map<String, Object> param = Map.of("id", id);
        Answer answer = template.queryForObject(sql, param, answerRowMapper());
        return answer != null ? Optional.of(answer) : Optional.empty();
    }

    private RowMapper<Answer> answerRowMapper() {
        return (rs, rowNum) -> new Answer(
                rs.getInt("answer_id"),
                rs.getString("content"),
                rs.getTimestamp("created_at").toLocalDateTime()
        );
    }
}

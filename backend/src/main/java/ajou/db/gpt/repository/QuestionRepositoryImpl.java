package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Question;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.Objects;

@Repository
public class QuestionRepositoryImpl implements QuestionRepository {

    private final NamedParameterJdbcTemplate template;

    public QuestionRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Question save(Question question) {
        String sql = "INSERT INTO question (user_id, answer_id, category, content, created_at) " +
                "VALUES (:user_id, :answer_id, :category, :content, :created_at)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", question.getUser().getId())
                .addValue("answer_id", question.getAnswer().getId())
                .addValue("category", question.getCategory().toString())
                .addValue("content", question.getContent())
                .addValue("created_at", LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);

        int key = Objects.requireNonNull(keyHolder.getKey()).intValue();
        question.setId(key);
        return question;
    }
}

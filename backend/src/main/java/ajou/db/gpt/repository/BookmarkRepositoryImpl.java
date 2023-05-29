package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Bookmark;
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
public class BookmarkRepositoryImpl implements BookmarkRepository {

    private final NamedParameterJdbcTemplate template;

    public BookmarkRepositoryImpl(DataSource dataSource) {
        this.template = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Bookmark save(Bookmark bookmark) {
        String sql = "INSERT INTO bookmark (user_id, question_id, created_at) " +
                "VALUES (:user_id, :question_id, :created_at)";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", bookmark.getUser().getId())
                .addValue("question_id", bookmark.getQuestion().getId())
                .addValue("created_at", LocalDateTime.now());
        KeyHolder keyHolder = new GeneratedKeyHolder();

        template.update(sql, param, keyHolder);

        int key = Objects.requireNonNull(keyHolder.getKey()).intValue();
        bookmark.setId(key);
        return bookmark;
    }

    @Override
    public boolean existsByUserIdAndQuestionId(String userId, Integer questionId) {
        String sql = """
                SELECT count(bookmark_id)
                FROM bookmark
                WHERE user_id = :user_id AND question_id = :question_id
                """;
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id", userId)
                .addValue("question_id", questionId);

        Integer count = template.queryForObject(sql, param, Integer.class);
        return count != null && count > 0;
    }
}

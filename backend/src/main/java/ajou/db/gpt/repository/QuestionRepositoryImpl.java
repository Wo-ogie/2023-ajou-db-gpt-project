package ajou.db.gpt.repository;

import ajou.db.gpt.constant.Category;
import ajou.db.gpt.constant.SortCond;
import ajou.db.gpt.domain.Answer;
import ajou.db.gpt.domain.Question;
import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.chat.QuestionWithAnswerRes;
import ajou.db.gpt.dto.chat.QuestionWithMarkedStatusRes;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    @Override
    public Optional<Question> findById(Integer id) {
        String sql = """
                SELECT q.question_id, q.user_id, q.answer_id, q.category, q.content, q.created_at,
                    u.user_id, u.password, u.name, u.created_at,
                    a.answer_id, a.content, a.created_at
                FROM question q
                      JOIN users u on u.user_id = q.user_id
                      JOIN answer a on a.answer_id = q.answer_id
                WHERE question_id = :question_id;
                """;

        Map<String, Object> param = Map.of("question_id", id);
        Question result = template.queryForObject(sql, param, questionRowMapper());
        return result != null ? Optional.of(result) : Optional.empty();
    }

    private RowMapper<Question> questionRowMapper() {
        return (rs, rowNum) -> new Question(
                rs.getInt("q.question_id"),
                new User(
                        rs.getString("u.user_id"),
                        rs.getString("u.password"),
                        rs.getString("u.name"),
                        rs.getTimestamp("u.created_at").toLocalDateTime()
                ),
                new Answer(
                        rs.getInt("a.answer_id"),
                        rs.getString("a.content"),
                        rs.getTimestamp("a.created_at").toLocalDateTime()
                ),
                Category.valueOf(rs.getString("q.category")),
                rs.getString("q.content"),
                rs.getTimestamp("q.created_at").toLocalDateTime()
        );
    }

    @Override
    public List<QuestionWithAnswerRes> searchQnAs(String userId, Category category, String keyword, SortCond sort, Boolean onlyMarked) {
        MapSqlParameterSource param = new MapSqlParameterSource().addValue("user_id", userId);

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT q.question_id, q.category, q.content, a.content, ");
        sql.append("IF(b.bookmark_id is not null, true, false) AS is_marked ");
        sql.append("FROM question q ");
        sql.append("JOIN answer a on a.answer_id = q.answer_id ");
        sql.append("LEFT JOIN bookmark b on q.question_id = b.question_id ");
        sql.append("WHERE q.user_id = :user_id ");

        if (category != null) {
            sql.append("AND q.category = :category ");
            param.addValue("category", category.toString());
        }

        if (keyword != null && !keyword.isEmpty()) {
            sql.append("AND (q.content LIKE :keyword OR a.content LIKE :keyword) ");
            param.addValue("keyword", "%" + keyword + "%");
        }

        if (onlyMarked) {
            sql.append("GROUP BY q.question_id, q.category, q.content, q.created_at, a.content, is_marked ");
            sql.append("HAVING is_marked = true ");
        }

        switch (sort) {
            case LATEST -> sql.append("ORDER BY q.created_at DESC");
            case OLDEST -> sql.append("ORDER BY q.created_at");
        }

        return template.query(sql.toString(), param, qnaRowMapper());
    }

    private RowMapper<QuestionWithAnswerRes> qnaRowMapper() {
        return (rs, rowNum) -> new QuestionWithAnswerRes(
                new QuestionWithMarkedStatusRes(
                        rs.getInt("q.question_id"),
                        Category.valueOf(rs.getString("q.category")),
                        rs.getString("q.content"),
                        rs.getBoolean("is_marked")
                ),
                rs.getString("a.content")
        );
    }
}

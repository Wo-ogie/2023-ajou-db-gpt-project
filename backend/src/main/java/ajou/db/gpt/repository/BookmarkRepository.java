package ajou.db.gpt.repository;

import ajou.db.gpt.domain.Bookmark;

public interface BookmarkRepository {

    Bookmark save(Bookmark bookmark);

    boolean existsByUserIdAndQuestionId(String userId, Integer questionId);

    void deleteByUserIdAndQuestionId(String userId, Integer questionId);
}

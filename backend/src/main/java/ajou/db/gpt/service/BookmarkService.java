package ajou.db.gpt.service;

import ajou.db.gpt.domain.Bookmark;
import ajou.db.gpt.domain.Question;
import ajou.db.gpt.domain.User;
import ajou.db.gpt.exception.bookmark.DuplicateBookmarkException;
import ajou.db.gpt.repository.BookmarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class BookmarkService {

    private final UserService userService;
    private final QuestionService questionService;
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Bookmark save(String userId, Integer questionId) {
        User user = userService.findById(userId);
        Question question = questionService.findById(questionId);

        if (bookmarkRepository.existsByUserIdAndQuestionId(userId, questionId)) {
            throw new DuplicateBookmarkException();
        }

        return bookmarkRepository.save(new Bookmark(user, question));
    }
}

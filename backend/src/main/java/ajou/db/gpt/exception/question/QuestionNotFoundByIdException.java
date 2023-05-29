package ajou.db.gpt.exception.question;

import ajou.db.gpt.exception.common.NotFoundException;

public class QuestionNotFoundByIdException extends NotFoundException {
    public QuestionNotFoundByIdException(Integer questionId) {
        super("question=" + questionId);
    }
}

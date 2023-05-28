package ajou.db.gpt.exception.user;

import ajou.db.gpt.exception.common.NotFoundException;

public class UserNotFoundByIdException extends NotFoundException {
    public UserNotFoundByIdException(String id) {
        super("id=" + id);
    }
}

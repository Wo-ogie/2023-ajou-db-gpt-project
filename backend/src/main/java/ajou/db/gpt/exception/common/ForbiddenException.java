package ajou.db.gpt.exception.common;

import ajou.db.gpt.exception.CustomException;
import org.springframework.http.HttpStatus;

public abstract class ForbiddenException extends CustomException {

    public ForbiddenException() {
        super(HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String optionalMessage) {
        super(HttpStatus.FORBIDDEN, optionalMessage);
    }

    public ForbiddenException(Throwable cause) {
        super(HttpStatus.FORBIDDEN, cause);
    }

    public ForbiddenException(String optionalMessage, Throwable cause) {
        super(HttpStatus.FORBIDDEN, optionalMessage, cause);
    }
}

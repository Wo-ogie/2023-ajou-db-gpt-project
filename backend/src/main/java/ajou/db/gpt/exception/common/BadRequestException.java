package ajou.db.gpt.exception.common;

import ajou.db.gpt.exception.CustomException;
import org.springframework.http.HttpStatus;

public abstract class BadRequestException extends CustomException {

    public BadRequestException() {
        super(HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String optionalMessage) {
        super(HttpStatus.BAD_REQUEST, optionalMessage);
    }

    public BadRequestException(Throwable cause) {
        super(HttpStatus.BAD_REQUEST, cause);
    }

    public BadRequestException(String optionalMessage, Throwable cause) {
        super(HttpStatus.BAD_REQUEST, optionalMessage, cause);
    }
}

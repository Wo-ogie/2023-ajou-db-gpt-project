package ajou.db.gpt.exception.auth;

import ajou.db.gpt.exception.common.UnauthorizedException;

public class TokenValidateException extends UnauthorizedException {

    public TokenValidateException() {
        super();
    }

    public TokenValidateException(String optionalMessage) {
        super(optionalMessage);
    }

    public TokenValidateException(Throwable cause) {
        super(cause);
    }
}

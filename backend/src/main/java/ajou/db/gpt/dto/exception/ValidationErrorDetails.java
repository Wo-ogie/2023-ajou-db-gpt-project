package ajou.db.gpt.dto.exception;

public record ValidationErrorDetails(
        Integer code,
        String field,
        String message
) {
}

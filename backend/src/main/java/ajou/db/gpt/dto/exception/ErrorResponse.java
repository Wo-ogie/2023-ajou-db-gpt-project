package ajou.db.gpt.dto.exception;

public record ErrorResponse(
        Integer code,
        String message
) {
}

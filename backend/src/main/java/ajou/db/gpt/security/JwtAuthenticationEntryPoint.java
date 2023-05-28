package ajou.db.gpt.security;

import ajou.db.gpt.dto.exception.ErrorResponse;
import ajou.db.gpt.exception.ExceptionType;
import ajou.db.gpt.exception.ExceptionUtils;
import ajou.db.gpt.logger.LogUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * 인증이 필요한 엔드포인트에 대해 인증되지 않았을 때 동작하는 handler.
     *
     * @param request                 that resulted in an <code>AuthenticationException</code>
     * @param response                so that the user agent can begin authentication
     * @param authenticationException that caused the invocation
     * @throws IOException if an input or output exception occurred
     */
    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authenticationException
    ) throws IOException {
        log.error(
                "[{}] JwtAuthenticationEntryPoint.commence() ex={}",
                LogUtils.getLogTraceId(),
                ExceptionUtils.getExceptionStackTrace(authenticationException)
        );

        response.setStatus(UNAUTHORIZED.value());
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(
                new ObjectMapper().writeValueAsString(
                        new ErrorResponse(
                                ExceptionType.ACCESS_DENIED.getCode(),
                                ExceptionType.ACCESS_DENIED.getMessage()
                        )
                )
        );
    }
}

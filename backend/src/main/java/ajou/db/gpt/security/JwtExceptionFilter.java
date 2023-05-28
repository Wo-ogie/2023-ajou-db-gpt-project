package ajou.db.gpt.security;

import ajou.db.gpt.dto.exception.ErrorResponse;
import ajou.db.gpt.exception.ExceptionType;
import ajou.db.gpt.exception.auth.TokenValidateException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (TokenValidateException ex) {
            setErrorResponse(ex.getClass(), response);
        }
    }

    /**
     * Exception 정보를 입력받아 응답할 error response를 설정한다.
     *
     * @param classType Exception의 class type
     * @param response  HttpServletResponse 객체
     */
    private void setErrorResponse(
            Class<? extends Exception> classType,
            HttpServletResponse response
    ) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json; charset=UTF-8");

        ExceptionType exceptionType = ExceptionType.from(classType).orElse(ExceptionType.UNAUTHORIZED);
        ErrorResponse errorResponse = new ErrorResponse(exceptionType.getCode(), exceptionType.getMessage());
        new ObjectMapper().writeValue(response.getOutputStream(), errorResponse);
    }
}

package ajou.db.gpt.service;

import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.auth.LoginRes;
import ajou.db.gpt.dto.redis.RefreshToken;
import ajou.db.gpt.dto.user.UserRes;
import ajou.db.gpt.exception.auth.PasswordNotValidException;
import ajou.db.gpt.repository.RefreshTokenRepository;
import ajou.db.gpt.security.JwtTokenInfoDto;
import ajou.db.gpt.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {

    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public LoginRes login(String id, String password) {
        User user = userService.findById(id);
        validatePassword(password, user);

        JwtTokenInfoDto accessToken = jwtTokenProvider.createAccessToken(id);
        JwtTokenInfoDto refreshToken = jwtTokenProvider.createRefreshToken(id);
        refreshTokenRepository.save(RefreshToken.of(refreshToken.token(), id));

        return new LoginRes(UserRes.from(user), accessToken.token(), accessToken.expiresAt(), refreshToken.token(), refreshToken.expiresAt());
    }

    private void validatePassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotValidException();
        }
    }
}

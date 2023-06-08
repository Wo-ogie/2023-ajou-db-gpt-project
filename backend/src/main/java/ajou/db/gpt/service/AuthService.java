package ajou.db.gpt.service;

import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.auth.LoginRes;
import ajou.db.gpt.dto.auth.response.TokenResponse;
import ajou.db.gpt.dto.redis.RefreshToken;
import ajou.db.gpt.dto.user.UserRes;
import ajou.db.gpt.exception.auth.PasswordNotValidException;
import ajou.db.gpt.exception.auth.TokenValidateException;
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

        TokenResponse tokens = createToken(id);

        return new LoginRes(UserRes.from(user), tokens.getAccessToken(), tokens.getAccessTokenExpiresAt(), tokens.getRefreshToken(), tokens.getRefreshTokenExpiresAt());
    }

    private void validatePassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new PasswordNotValidException();
        }
    }

    @Transactional
    public TokenResponse refresh(String oldRefreshToken) {
        jwtTokenProvider.validateToken(oldRefreshToken);

        RefreshToken oldRedisRefreshToken = refreshTokenRepository.findById(oldRefreshToken)
                .orElseThrow(TokenValidateException::new);
        refreshTokenRepository.delete(oldRedisRefreshToken);

        return createToken(oldRedisRefreshToken.getUserId());
    }

    private TokenResponse createToken(String id) {
        JwtTokenInfoDto accessToken = jwtTokenProvider.createAccessToken(id);
        JwtTokenInfoDto refreshToken = jwtTokenProvider.createRefreshToken(id);
        refreshTokenRepository.save(RefreshToken.of(refreshToken.token(), id));

        return new TokenResponse(accessToken.token(), accessToken.expiresAt(), refreshToken.token(), refreshToken.expiresAt());
    }
}

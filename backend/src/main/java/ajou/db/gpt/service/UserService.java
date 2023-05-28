package ajou.db.gpt.service;

import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.user.UserCreateReq;
import ajou.db.gpt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(UserCreateReq userReq) {
        User user = userReq.toEntity(passwordEncoder.encode(userReq.getPassword()));
        return userRepository.save(user);
    }
}

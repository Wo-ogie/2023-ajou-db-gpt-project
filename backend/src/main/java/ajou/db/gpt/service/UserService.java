package ajou.db.gpt.service;

import ajou.db.gpt.domain.User;
import ajou.db.gpt.dto.user.UserCreateReq;
import ajou.db.gpt.exception.user.UserNotFoundByIdException;
import ajou.db.gpt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public User save(UserCreateReq userReq) {
        User user = userReq.toEntity(passwordEncoder.encode(userReq.getPassword()));
        return userRepository.save(user);
    }

    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));
    }
}

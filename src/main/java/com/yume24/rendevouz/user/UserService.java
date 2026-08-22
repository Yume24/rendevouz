package com.yume24.rendevouz.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Mono<UserDTO> createAnonymousUser(String username) {
        var anonymousUser = User.builder().username(username).role(User.UserRole.ANONYMOUS).build();
        return userRepository.save(anonymousUser).map(userMapper::toDto);
    }
}

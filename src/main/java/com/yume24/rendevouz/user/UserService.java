package com.yume24.rendevouz.user;

import com.yume24.rendevouz.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UUIDService uuidService;

    public UserDTO createAnnonymousUser(String name) {
        var userID = uuidService.generateUUID();
        return new UserDTO(userID, name);
    }
}

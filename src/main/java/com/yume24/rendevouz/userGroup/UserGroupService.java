package com.yume24.rendevouz.userGroup;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserGroupService {
    private final UserGroupRepository userGroupRepository;

    public Mono<Void> addUserToGroup(UUID userId, UUID groupId) {
        var key = new UserGroupKey(userId, groupId);
        return checkUserAlreadyInGroup(key).then(
                userGroupRepository.save(new UserGroup(key))
        ).then();
    }

    private Mono<Void> checkUserAlreadyInGroup(UserGroupKey key) {
        return userGroupRepository.existsById(key).handle((exists, sink) -> {
            if (exists) sink.error(new UserAlreadyInGroupException(key.toString()));
            else sink.complete();
        });
    }
}

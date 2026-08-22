package com.yume24.rendevouz.groupMembership;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupMembershipService {
    private final GroupMembershipRepository groupMembershipRepository;

    public Mono<Void> addUserToGroup(UUID userId, UUID groupId) {
        var key = new GroupMembershipKey(userId, groupId);
        return checkUserAlreadyInGroup(key).then(
                groupMembershipRepository.save(new GroupMembership(key))
        ).then();
    }

    private Mono<Void> checkUserAlreadyInGroup(GroupMembershipKey key) {
        return groupMembershipRepository.existsById(key).handle((exists, sink) -> {
            if (exists) sink.error(new UserAlreadyInGroupException(key.toString()));
            else sink.complete();
        });
    }
}

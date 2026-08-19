package com.yume24.rendevouz.group;

import com.yume24.rendevouz.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final UUIDService uuidService;
    private final ReactiveRedisOperations<String, String> redisOperations;

    public Mono<GroupDTO> createGroup(String userId) {
        var groupID = uuidService.generateUUID();
        var group = new GroupDTO(groupID);

        return redisOperations.opsForSet().add(groupID, userId).thenReturn(group);
    }

    public Mono<Void> joinGroup(String groupId, String userId) {
        return redisOperations.opsForSet().size(groupId)
                .flatMap(s -> {
                    if (s == 0) return Mono.error(new RuntimeException());
                    return redisOperations.opsForSet().add(groupId, userId);
                })
                .then();
    }

    public Flux<String> getGroupMembers(String groupId) {
        return redisOperations.opsForSet().members(groupId).switchIfEmpty(Flux.error(new RuntimeException()));
    }
}

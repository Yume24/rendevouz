package com.yume24.rendevouz.group;

import com.yume24.rendevouz.redis.RedisConfiguration;
import com.yume24.rendevouz.location.UserLocationDTO;
import com.yume24.rendevouz.uuid.UUIDService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class GroupService {
    private static final String GROUP_KEY = "group";
    private static final String LOCATION = "location";

    private final UUIDService uuidService;
    private final ReactiveRedisOperations<String, Boolean> redisOperationsGroup;
    private final ReactiveRedisOperations<String, UserLocationDTO> redisOperationsLocation;

    public Mono<GroupDTO> createGroup() {
        var groupID = uuidService.generateUUID();
        var group = new GroupDTO(groupID);

        return redisOperationsGroup.opsForValue().set(createGroupKey(groupID), true).thenReturn(group);
    }

    public Mono<Void> joinGroup(String groupId, UserLocationDTO user) {
        return checkGroupExists(groupId)
                .then(redisOperationsLocation.opsForHash().putIfAbsent(createLocationKey(groupId), user.id(), user))
                .flatMap(success -> success ? Mono.empty() : Mono.error(new UserAlreadyJoinedException(user.id())));
    }

    public Flux<UserLocationDTO> getGroupMembers(String groupId) {
        return checkGroupExists(groupId)
                .thenMany(
                    redisOperationsLocation
                    .opsForHash()
                    .entries(createLocationKey(groupId))
                    .map(Map.Entry::getValue)
                    .cast(UserLocationDTO.class));
    }

    private Mono<Void> checkGroupExists(String groupId) {
        return redisOperationsGroup.opsForValue().get(createGroupKey(groupId)).switchIfEmpty(Mono.error(new NoSuchGroupException(groupId))).then();
    }

    private String createLocationKey(String groupId) {
        return GROUP_KEY + RedisConfiguration.KEY_DELIMITER + groupId + RedisConfiguration.KEY_DELIMITER + LOCATION;
    }

    private String createGroupKey(String groupId) {
        return GROUP_KEY + RedisConfiguration.KEY_DELIMITER + groupId;
    }
}

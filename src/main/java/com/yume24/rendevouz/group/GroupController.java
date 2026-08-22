package com.yume24.rendevouz.group;

import com.yume24.rendevouz.groupMembership.GroupMembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final GroupMembershipService groupMembershipService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<GroupDTO> createGroup(@RequestBody GroupCreateRequest groupCreateRequest, @AuthenticationPrincipal Jwt jwt) {
        var userId = UUID.fromString(Objects.requireNonNull(jwt.getSubject()));
        return groupService.createGroup(groupCreateRequest.name(), userId);
    }

    @PostMapping("/{groupId}/join")
    Mono<Void> joinGroup(@PathVariable UUID groupId, @AuthenticationPrincipal Jwt jwt) {
        var userId = UUID.fromString(Objects.requireNonNull(jwt.getSubject()));
        return groupService.checkIfGroupExists(groupId).then(
                groupMembershipService.addUserToGroup(userId, groupId)
        );
    }
}

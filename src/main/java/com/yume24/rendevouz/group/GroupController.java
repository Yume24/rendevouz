package com.yume24.rendevouz.group;

import com.yume24.rendevouz.token.TokenDTO;
import com.yume24.rendevouz.userGroup.UserGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final UserGroupService userGroupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<GroupDTO> createGroup() {
        return groupService.createGroup();
    }

    @PostMapping("/{id}/join")
    Mono<TokenDTO> joinGroup(@PathVariable UUID id) {
        return groupService.checkIfGroupExists(id).then(
                userGroupService.addUserToGroup()
        )
    }
}

package com.yume24.rendevouz.group;

import com.yume24.rendevouz.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;
    private final UserService userService;

    @GetMapping("/{id}")
    Flux<String> getMembers(@PathVariable String id) {
        return groupService.getGroupMembers(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<GroupDTO> createGroup() {
        var user = userService.createAnnonymousUser();
        return groupService.createGroup(user.id());
    }

    @PostMapping("/{id}/join")
    Mono<Void> joinGroup(@PathVariable String id) {
        var user = userService.createAnnonymousUser();
        return groupService.joinGroup(id, user.id());
    }
}

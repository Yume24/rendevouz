package com.yume24.rendevouz.group;

import com.yume24.rendevouz.token.TokenDTO;
import com.yume24.rendevouz.token.TokenService;
import com.yume24.rendevouz.location.UserLocationDTO;
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
    private final TokenService tokenService;

    @GetMapping("/{id}")
    Flux<UserLocationDTO> getMembers(@PathVariable String id) {
        return groupService.getGroupMembers(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Mono<GroupDTO> createGroup() {
        return groupService.createGroup();
    }

    @PostMapping("/{id}/join")
    Mono<TokenDTO> joinGroup(@PathVariable String id, @RequestBody UserLocationDTO userLocation) {
        var user = userService.createAnonymousUser(userLocation.id());
        return groupService.joinGroup(id, userLocation).thenReturn(tokenService.createTokens(user));
    }
}

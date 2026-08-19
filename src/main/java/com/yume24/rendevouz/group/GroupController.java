package com.yume24.rendevouz.group;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/group")
public class GroupController {
    @GetMapping("/{id}")
    Mono<Void> getMembers(@PathVariable String id) {
        return Mono.empty();
    }

    @PostMapping
    Mono<Void> createGroup() {
        return Mono.empty();
    }

    @PostMapping("/{id}/join")
    Mono<Void> joinGroup(@PathVariable String id) {
        return Mono.empty();
    }
}

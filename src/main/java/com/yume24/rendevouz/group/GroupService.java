package com.yume24.rendevouz.group;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public Mono<GroupDTO> createGroup() {
        return groupRepository.save(new Group()).map(groupMapper::toDto);
    }

    public Mono<Void> checkIfGroupExists(UUID groupID) {
        return groupRepository
                .existsById(groupID)
                .handle((exists, sink) -> {
                    if (exists) {
                        sink.complete();
                    } else {
                        sink.error(new GroupDoesNotExistsException(groupID.toString()));
                    }
                });
    }
}

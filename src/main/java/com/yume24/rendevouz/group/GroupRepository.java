package com.yume24.rendevouz.group;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GroupRepository extends ReactiveCrudRepository<Group, UUID> {
}

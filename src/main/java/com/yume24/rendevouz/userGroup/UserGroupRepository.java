package com.yume24.rendevouz.userGroup;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserGroupRepository extends ReactiveCrudRepository<UserGroup, UserGroupKey> {
}

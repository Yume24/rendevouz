package com.yume24.rendevouz.groupMembership;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupMembershipRepository extends ReactiveCrudRepository<GroupMembership, GroupMembershipKey> {
}

package com.yume24.rendevouz.userGroup;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

record UserGroupKey(UUID userId, UUID groupId) {}

@Table("users_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    @Id
    private UserGroupKey id;
}

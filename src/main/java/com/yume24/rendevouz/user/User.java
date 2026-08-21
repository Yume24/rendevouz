package com.yume24.rendevouz.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("users")
@Builder
@Getter
@Setter
public class User {
    @Id
    private UUID ID;

    @Column("username")
    private String username;

    @Column("created_at")
    private Instant createdAt;
}

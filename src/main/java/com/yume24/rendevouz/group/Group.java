package com.yume24.rendevouz.group;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.util.UUID;

@Table("groups")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Group {
    @Id
    private UUID ID;

    @Column("name")
    private String name;

    @Column("created_at")
    private Instant createdAt;

    @Column("created_by")
    private UUID createdBy;
}

package com.yume24.rendevouz.group;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("groups")
public class Group {
    @Id
    private UUID ID;
}

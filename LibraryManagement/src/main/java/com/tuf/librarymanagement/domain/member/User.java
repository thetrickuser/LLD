package com.tuf.librarymanagement.domain.member;

import lombok.Data;

import java.util.UUID;

@Data
public class User {
    private UUID id;
    private String tenantId;
    private String name;
    private String email;
    private Role role;
}

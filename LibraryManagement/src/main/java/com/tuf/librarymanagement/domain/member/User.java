package com.tuf.librarymanagement.domain.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private UUID id;
    private String tenantId;
    private String name;
    private String email;
    private Role role;
}

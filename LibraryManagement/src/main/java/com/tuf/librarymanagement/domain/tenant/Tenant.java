package com.tuf.librarymanagement.domain.tenant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Tenant {
    private String id;
    private String name;
    private TenantConfig config;
}

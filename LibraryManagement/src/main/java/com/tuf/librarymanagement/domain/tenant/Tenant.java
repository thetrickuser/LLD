package com.tuf.librarymanagement.domain.tenant;

import lombok.Data;

@Data
public class Tenant {

    private String id;
    private String name;
    private TenantConfig config;
}

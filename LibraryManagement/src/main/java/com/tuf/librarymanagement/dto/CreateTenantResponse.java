package com.tuf.librarymanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CreateTenantResponse
 */
@AllArgsConstructor
@Getter
public class CreateTenantResponse {
    private String tenantId;
    private String name;
}

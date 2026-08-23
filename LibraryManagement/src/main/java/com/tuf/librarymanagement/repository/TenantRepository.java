package com.tuf.librarymanagement.repository;

import com.tuf.librarymanagement.domain.tenant.Tenant;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class TenantRepository {

    private ConcurrentHashMap<String, Tenant> tenants;

    public TenantRepository() {
        tenants = new ConcurrentHashMap<>();
    }

    public Tenant save(Tenant tenant) {
        return tenants.put(tenant.getId(), tenant);
    }

    public Optional<Tenant> findById(String tenantId) {
        return Optional.ofNullable(tenants.get(tenantId));
    }

    public void deleteById(String tenantId) {
        tenants.remove(tenantId);
    }

    public boolean existsById(String tenantId) {
        return tenants.containsKey(tenantId);
    }

    public int getNumberOfTenants() {
        return tenants.size();
    }
}

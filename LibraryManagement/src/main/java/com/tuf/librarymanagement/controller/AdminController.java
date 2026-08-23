package com.tuf.librarymanagement.controller;

import com.tuf.librarymanagement.domain.member.Member;
import com.tuf.librarymanagement.domain.tenant.Tenant;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;
import com.tuf.librarymanagement.domain.tenant.fine.FineStrategyType;
import com.tuf.librarymanagement.dto.CreateTenantRequest;
import com.tuf.librarymanagement.dto.CreateTenantResponse;
import com.tuf.librarymanagement.dto.UpdateTenantRequest;
import com.tuf.librarymanagement.dto.UpdateTenantResponse;
import com.tuf.librarymanagement.service.AdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public Tenant getTenant(String tenantId) {
        log.info("Received request to get tenant with ID: {}", tenantId);
        return adminService.getTenant(tenantId);
    }

    public CreateTenantResponse addTenant(CreateTenantRequest request) {
        log.info("Received request to add new tenant: {}", request.name());
        TenantConfig config = new TenantConfig(
                request.maxBooksAllowed(),
                request.defaultLoanPeriodDays(),
                FineStrategyType.valueOf(request.fineStrategyType()),
                request.flatFinePerDay(),
                request.reservationHoldHours(),
                request.baseTierFineDays()
        );
        Tenant tenant = adminService.createTenant(request.name(), config);
        return new CreateTenantResponse(tenant.getId(), tenant.getName());
    }

    public void removeTenant(String tenantId) {
        log.info("Received request to remove tenant with ID: {}", tenantId);
        adminService.deleteTenant(tenantId);
        log.info("Tenant with ID: {} removed successfully.", tenantId);
    }

    public UpdateTenantResponse updateTenantConfig(UpdateTenantRequest request) {
        log.info("Received request to update tenant config for ID: {}", request.tenantId());
        TenantConfig newConfig = new TenantConfig(
                request.maxBooksAllowed(),
                request.defaultLoanPeriodDays(),
                FineStrategyType.valueOf(request.fineStrategyType()),
                request.flatFinePerDay(),
                request.reservationHoldHours(),
                request.baseTierFineDays()
        );
        Tenant updatedTenant = adminService.updateTenantConfig(request.tenantId(), newConfig);
        return new UpdateTenantResponse(
                updatedTenant.getId(),
                updatedTenant.getConfig().getMaxBooksAllowed(),
                updatedTenant.getConfig().getDefaultLoanPeriodDays(),
                updatedTenant.getConfig().getFineStrategyType().name(),
                updatedTenant.getConfig().getFlatFinePerDay(),
                updatedTenant.getConfig().getReservationHoldHours(),
                updatedTenant.getConfig().getBaseTierFineDays()
        );
    }

    public Member addLibrarian(String tenantId, String name, String email) {
        log.info("Received request to add librarian {} to tenant with ID: {}", name, tenantId);
        Member librarian = adminService.addLibrarianToTenant(tenantId, name, email);
        log.info("Librarian {} added to tenant with ID: {}", name, tenantId);
        return librarian;
    }

    public Member removeLibrarian(String tenantId, String membershipId) {
        log.info("Received request to remove librarian with membership ID: {} from tenant with ID: {}", membershipId, tenantId);
        Member removedLibrarian = adminService.removeLibrarianFromTenant(tenantId, membershipId);
        log.info("Librarian with membership ID: {} removed from tenant with ID: {}", membershipId, tenantId);
        return removedLibrarian;
    }
}

package com.tuf.librarymanagement.service;

import com.tuf.librarymanagement.domain.member.Member;
import com.tuf.librarymanagement.domain.member.Role;
import com.tuf.librarymanagement.domain.tenant.Tenant;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;
import com.tuf.librarymanagement.repository.MemberRepository;
import com.tuf.librarymanagement.repository.TenantRepository;
import com.tuf.librarymanagement.util.MemberUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdminService {

    private final TenantRepository tenantRepository;
    private final MemberRepository memberRepository;

    public AdminService(TenantRepository tenantRepository, MemberRepository memberRepository) {
        this.tenantRepository = tenantRepository;
        this.memberRepository = memberRepository;
    }

    public Tenant createTenant(String name, TenantConfig config) {
        String tenantId = "T-" + tenantRepository.getNumberOfTenants();
        log.info("Creating tenant with ID: {} and name: {}", tenantId, name);
        Tenant tenant = new Tenant(tenantId, name, config);
        tenantRepository.save(tenant);
        log.info("Tenant created successfully with ID: {}", tenant.getId());
        return tenant;
    }

    public void deleteTenant(String tenantId) {
        if (!tenantRepository.existsById(tenantId)) {
            log.warn("Attempted to delete non-existent tenant with ID: {}", tenantId);
            throw new IllegalArgumentException("Tenant with ID " + tenantId + " does not exist.");
        }
        tenantRepository.deleteById(tenantId);
        log.info("Tenant with ID: {} deleted successfully.", tenantId);
    }

    public Tenant updateTenantConfig(String tenantId, TenantConfig newConfig) {
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> {
                    log.warn("Attempted to update non-existent tenant with ID: {}", tenantId);
                    return new IllegalArgumentException("Tenant with ID " + tenantId + " does not exist.");
                });
        Tenant updatedTenant = new Tenant(tenant.getId(), tenant.getName(), newConfig);
        tenantRepository.save(updatedTenant);
        log.info("Tenant with ID: {} updated successfully.", tenantId);
        return updatedTenant;
    }

    public Tenant getTenant(String tenantId) {
        return tenantRepository.findById(tenantId)
                .orElseThrow(() -> {
                    log.warn("Attempted to retrieve non-existent tenant with ID: {}", tenantId);
                    return new IllegalArgumentException("Tenant with ID " + tenantId + " does not exist.");
                });
    }

    public Member addLibrarianToTenant(String tenantId, String name, String email) {
        if (!tenantRepository.existsById(tenantId)) {
            log.warn("Attempted to add librarian to non-existent tenant with ID: {}", tenantId);
            throw new IllegalArgumentException("Tenant with ID " + tenantId + " does not exist.");
        };

        String membershipId = MemberUtil.generateMembershipId(tenantId, Role.LIBRARIAN, memberRepository.getMemberCountByRole(tenantId, Role.LIBRARIAN));
        Member librarian = new Member(tenantId, name, email, Role.LIBRARIAN, membershipId);
        memberRepository.save(librarian);
        log.info("Librarian {} added to tenant with ID: {}", name, tenantId);
        return librarian;
    }

    public Member removeLibrarianFromTenant(String tenantId, String membershipId) {
        if (!memberRepository.existsById(membershipId)) {
            log.warn("Attempted to remove non-existent librarian with membership ID: {} from tenant with ID: {}", membershipId, tenantId);
            throw new IllegalArgumentException("Librarian with membership ID " + membershipId + " does not exist.");
        }
        memberRepository.deactivateMember(membershipId);
        log.info("Librarian with membership ID: {} deactivated from tenant with ID: {}", membershipId, tenantId);
        return memberRepository.findById(membershipId);
    }
}

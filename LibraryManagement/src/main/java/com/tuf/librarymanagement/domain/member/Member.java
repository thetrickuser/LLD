package com.tuf.librarymanagement.domain.member;

import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Member extends User {
    private String membershipId;

    @Setter
    private MembershipStatus status;

    @Setter
    private int currentBorrowedBooksCount;

    @Setter
    private double totalFineOwed;

    public Member(String tenantId, String name, String email, Role role, String membershipId) {
        super(UUID.randomUUID(), tenantId, name, email, role);
        this.membershipId = membershipId;
        this.status = MembershipStatus.ACTIVE;
        this.currentBorrowedBooksCount = 0;
        this.totalFineOwed = 0.0;
    }
}

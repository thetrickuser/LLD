package com.tuf.librarymanagement.domain.member;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Member extends User {
    private String membershipId;
    private MembershipStatus status;
    private int currentBorrowedBooksCount;
    private double totalFineOwed;
}

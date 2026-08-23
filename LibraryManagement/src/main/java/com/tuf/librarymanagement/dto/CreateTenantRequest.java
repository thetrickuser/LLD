package com.tuf.librarymanagement.dto;

public record CreateTenantRequest(
        String name,
        int maxBooksAllowed,
        int defaultLoanPeriodDays,
        String fineStrategyType,
        double flatFinePerDay,
        int reservationHoldHours,
        int baseTierFineDays
) {

}

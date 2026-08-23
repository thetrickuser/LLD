package com.tuf.librarymanagement.dto;

public record UpdateTenantResponse(
    String tenantId, 
    int maxBooksAllowed, 
    int defaultLoanPeriodDays, 
    String fineStrategyType, 
    double flatFinePerDay, 
    int reservationHoldHours, 
    int baseTierFineDays
) {

}

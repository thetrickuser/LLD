package com.tuf.librarymanagement.dto;

public record UpdateTenantRequest(
    String tenantId, 
    int maxBooksAllowed, 
    int defaultLoanPeriodDays, 
    String fineStrategyType, 
    double flatFinePerDay, 
    int reservationHoldHours, 
    int baseTierFineDays) {

}

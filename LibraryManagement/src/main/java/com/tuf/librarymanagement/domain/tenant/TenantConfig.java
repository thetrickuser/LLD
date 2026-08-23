package com.tuf.librarymanagement.domain.tenant;

import com.tuf.librarymanagement.domain.tenant.fine.FineStrategyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TenantConfig {
    private int maxBooksAllowed;
    private int defaultLoanPeriodDays;
    private FineStrategyType fineStrategyType;
    private double flatFinePerDay;
    private int reservationHoldHours;
    private int baseTierFineDays;
}

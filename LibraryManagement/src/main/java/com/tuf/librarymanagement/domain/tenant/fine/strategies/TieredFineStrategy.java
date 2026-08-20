package com.tuf.librarymanagement.domain.tenant.fine.strategies;

import com.tuf.librarymanagement.domain.book.BookFormat;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;
import com.tuf.librarymanagement.domain.tenant.fine.FineStrategy;

public class TieredFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(int overdueDays, TenantConfig config, BookFormat bookFormat) {
        if (overdueDays <= 0) return 0.0;

        double fine = 0.0;
        int baseTierFineDays = config.getBaseTierFineDays();

        int baseDays = Math.min(overdueDays, baseTierFineDays);
        fine += baseDays * config.getFlatFinePerDay();

        if (overdueDays > baseTierFineDays) {
            int extraDays = overdueDays - baseTierFineDays;
            fine += extraDays * (config.getFlatFinePerDay() * 2);
        }

        return fine;
    }
}

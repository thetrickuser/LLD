package com.tuf.librarymanagement.domain.tenant.fine.strategies;

import com.tuf.librarymanagement.domain.book.BookFormat;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;
import com.tuf.librarymanagement.domain.tenant.fine.FineStrategy;

public class FlatDailyFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(int overdueDays, TenantConfig config, BookFormat bookFormat) {
        if (overdueDays <= 0) return 0.0;
        return overdueDays * config.getFlatFinePerDay();
    }
}

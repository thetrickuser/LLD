package com.tuf.librarymanagement.domain.tenant.fine.strategies;

import com.tuf.librarymanagement.domain.book.BookFormat;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;
import com.tuf.librarymanagement.domain.tenant.fine.FineStrategy;

public class FormatBasedFineStrategy implements FineStrategy {
    @Override
    public double calculateFine(int overdueDays, TenantConfig config, BookFormat bookFormat) {
        if (overdueDays <= 0) return 0.0;

        double multiplier = switch (bookFormat) {
            case HARDCOVER -> 1.5;
            default -> 1.0;
        };

        return overdueDays * multiplier * config.getFlatFinePerDay();
    }
}

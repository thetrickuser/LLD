package com.tuf.librarymanagement.domain.tenant.fine;

import com.tuf.librarymanagement.domain.tenant.fine.strategies.FlatDailyFineStrategy;
import com.tuf.librarymanagement.domain.tenant.fine.strategies.FormatBasedFineStrategy;
import com.tuf.librarymanagement.domain.tenant.fine.strategies.TieredFineStrategy;

public class FineStrategyFactory {
    public static FineStrategy getStrategy(FineStrategyType type) {
        return switch (type) {
            case TIERED -> new TieredFineStrategy();
            case FORMAT_BASED -> new FormatBasedFineStrategy();
            case FLAT_DAILY -> new FlatDailyFineStrategy();
        };
    }
}

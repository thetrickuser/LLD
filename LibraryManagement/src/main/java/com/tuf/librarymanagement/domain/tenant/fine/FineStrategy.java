package com.tuf.librarymanagement.domain.tenant.fine;

import com.tuf.librarymanagement.domain.book.BookFormat;
import com.tuf.librarymanagement.domain.tenant.TenantConfig;

public interface FineStrategy {
    double calculateFine(int overdueDays, TenantConfig config, BookFormat bookFormat);
}

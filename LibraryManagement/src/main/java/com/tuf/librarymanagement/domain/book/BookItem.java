package com.tuf.librarymanagement.domain.book;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookItem {
    private UUID id;
    private String barcode;
    private String tenantId;
    private String isbn;
    private BookStatus status;
    private LocalDate dueDate;
    private UUID currentBorrowerId;
}

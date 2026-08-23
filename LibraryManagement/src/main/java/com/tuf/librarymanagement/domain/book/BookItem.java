package com.tuf.librarymanagement.domain.book;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookItem {
    private UUID id;
    private String barcode;
    private String tenantId;
    private String isbn;
    private BookStatus status;
    private LocalDate dueDate;
    private UUID currentBorrowerId;
}

package com.tuf.librarymanagement.domain.book;

import lombok.*;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookEdition {
    private String isbn;
    private UUID bookId;
    private BookFormat format;
    private double cost;
}

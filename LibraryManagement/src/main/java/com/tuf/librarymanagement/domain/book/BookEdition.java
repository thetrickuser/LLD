package com.tuf.librarymanagement.domain.book;

import lombok.Data;

import java.util.UUID;

@Data
public class BookEdition {
    private String isbn;
    private UUID bookId;
    private BookFormat format;
    private double cost;
}

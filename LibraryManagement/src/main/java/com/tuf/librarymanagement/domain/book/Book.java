package com.tuf.librarymanagement.domain.book;

import java.util.UUID;

public record Book(
        UUID id,
        String title,
        String author) {
}

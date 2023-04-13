package org.example2.dto;

import lombok.Builder;

@Builder
public record AuthorDetails(
        String name,
        int bookCount,
        int uniqueCoAuthorsCount
) {
}

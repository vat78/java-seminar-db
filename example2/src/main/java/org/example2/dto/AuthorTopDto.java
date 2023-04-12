package org.example2.dto;

public record AuthorTopDto(
        String name,
        Integer bookCount,
        String genre,
        int rank
) {
}

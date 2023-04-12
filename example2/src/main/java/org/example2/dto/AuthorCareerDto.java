package org.example2.dto;

public record AuthorCareerDto (
        String name,
        int bookCount,
        String startYear,
        String endYear
) {
}

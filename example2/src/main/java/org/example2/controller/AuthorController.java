package org.example2.controller;

import lombok.RequiredArgsConstructor;
import org.example2.dto.AuthorCareerDto;
import org.example2.dto.AuthorDetails;
import org.example2.dto.AuthorTopDto;
import org.example2.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/author/{id}")
    public AuthorDetails getAuthorDetails(@PathVariable Long id) {
        return authorService.getAuthorDetailsV1(id);
    }

    @GetMapping("/authors")
    public List<String> getAuthorsByName(@RequestParam("name") String name) {
        return authorService.findAllByName(name);
    }

    @GetMapping("/authors/career")
    public List<AuthorCareerDto> getAuthorCareer() {
        return authorService.getAuthorCareer();
    }

    @GetMapping("/authors/rank")
    public List<AuthorTopDto> getRatingByGenres(@RequestParam("start") String startYear,
                                                @RequestParam("end") String endYear) {
        return authorService.getRankByGenres(startYear, endYear);
    }
}

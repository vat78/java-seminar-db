package org.example2.service;

import lombok.RequiredArgsConstructor;
import org.example2.dto.AuthorCareerDto;
import org.example2.dto.AuthorDetails;
import org.example2.dto.AuthorTopDto;
import org.example2.entity.Author;
import org.example2.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    @Transactional
    public AuthorDetails getAuthorDetailsV1(Long id) {
        var author = authorRepository.findById(id).orElseThrow();
        var bookCount = author.getBooks().size();
        var coautors = author.getBooks().stream()
                .flatMap(b -> b.getAuthors().stream())
                .collect(Collectors.toSet())
                .size();
        return new AuthorDetails(author.getName(), bookCount, coautors);
    }

    @Transactional
    public List<String> findAllByName(String name) {
        return authorRepository.findAllByNameContaining(name)
                .stream()
                .map(Author::getName)
                .toList();
    }

    @Transactional
    public List<AuthorCareerDto> getAuthorCareer() {
        return authorRepository.getAuthorCareer().stream()
                .map(authorCareer -> new AuthorCareerDto(
                        authorCareer.getName(),
                        authorCareer.getBookCount() == null ? 0 : authorCareer.getBookCount(),
                        authorCareer.getStartYear() == null ? "-" : authorCareer.getStartYear().toString(),
                        authorCareer.getEndYear() == null ? "-" : authorCareer.getEndYear().toString()
                ))
                .toList();
    }

    @Transactional
    public List<AuthorTopDto> getRankByGenres(String startYear, String endYear) {
        return authorRepository.getAuthorRating(startYear, endYear).stream()
                .map(authorTop -> new AuthorTopDto(
                        authorTop.getName(),
                        authorTop.getBookCount() == null ? 0 : authorTop.getBookCount(),
                        authorTop.getGenre(),
                        authorTop.getRank() == null ? 0 : authorTop.getRank()
                ))
                .toList();
    }
}

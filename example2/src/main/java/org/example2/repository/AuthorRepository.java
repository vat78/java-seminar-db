package org.example2.repository;

import org.example2.entity.Author;
import org.example2.entity.AuthorCareer;
import org.example2.entity.AuthorRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAllByNameContaining(String name);


    @Query(value = """
            select * from authors
            """, nativeQuery = true)
    List<AuthorCareer> getAuthorCareer();

    @Query(value = """
            select * from authors
            """, nativeQuery = true)
    List<AuthorRating> getAuthorRating(String startYear, String endYear);
}

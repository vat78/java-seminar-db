package org.example2.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Data
@Entity(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String caption;
    private String description;
    private String genre;
    private String year;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    private Set<Author> authors;

}

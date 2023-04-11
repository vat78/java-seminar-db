package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.repository.AuthorRepository;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class DataGenerator {
    private static final String[] GENRES = {"drama", "detective", "science"};

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    List<Book> bookList = new CopyOnWriteArrayList<>();

    @Transactional
    public void generateData() {
        for (int i=0; i<1000; i++) {
            bookList.add(bookRepository.save(generateBook()));
            authorRepository.save(generateAuthor());
        }
    }

    private Author generateAuthor() {
        var author = new Author();
        author.setName(generateText(3));
        author.setBooks(new HashSet<>());

        int books = ThreadLocalRandom.current().nextInt(15);
        for (int i=0; i<books; i++) {
            author.getBooks().add(bookList.get(ThreadLocalRandom.current().nextInt(bookList.size())));
        }
        return author;
    }

    private Book generateBook() {
        var book = new Book();
        book.setCaption(generateText(5));
        book.setDescription(generateText(50));
        book.setGenre(GENRES[ThreadLocalRandom.current().nextInt(GENRES.length)]);
        book.setYear(String.valueOf(ThreadLocalRandom.current().nextInt(1900, 2025)));
        return book;
    }

    private String generateText(int maxWordsCount) {
        return IntStream.range(0, ThreadLocalRandom.current().nextInt(maxWordsCount -1) + 1)
                .mapToObj(i -> generateWord())
                .collect(Collectors.joining(" "));
    }

    private String generateWord() {
        int size = ThreadLocalRandom.current().nextInt(4, 20);
        var result = new StringBuilder()
                .append(randomLetter(true));
        IntStream.range(0, size)
                .mapToObj(i -> randomLetter(false))
                .forEach(result::append);
        return result.toString();
    }

    private char randomLetter(boolean upperCase) {
        char letter = (char)(ThreadLocalRandom.current().nextInt(26) + 'a');
        return upperCase ? Character.toUpperCase(letter) : letter;
    }

}

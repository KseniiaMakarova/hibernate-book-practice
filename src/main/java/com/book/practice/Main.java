package com.book.practice;

import com.book.practice.lib.Injector;
import com.book.practice.model.Author;
import com.book.practice.model.Book;
import com.book.practice.model.Genre;
import com.book.practice.service.AuthorService;
import com.book.practice.service.BookService;
import com.book.practice.service.GenreService;
import java.time.LocalDate;

public class Main {
    private static final Injector INJECTOR = Injector.getInstance("com.book.practice");
    private static BookService bookService
            = (BookService) INJECTOR.getInstance(BookService.class);
    private static AuthorService authorService
            = (AuthorService) INJECTOR.getInstance(AuthorService.class);
    private static GenreService genreService
            = (GenreService) INJECTOR.getInstance(GenreService.class);

    public static void main(String[] args) {
        Genre dystopia = new Genre();
        dystopia.setName("Dystopian novel");
        Genre satire = new Genre();
        satire.setName("Satire");
        genreService.add(dystopia);
        genreService.add(satire);

        Author zamyatin = new Author();
        zamyatin.setName("Yevgeny Zamyatin");
        zamyatin.setDateOfBirth(LocalDate.of(1884, 2, 1));
        Author bulgakov = new Author();
        bulgakov.setName("Mikhail Bulgakov");
        bulgakov.setDateOfBirth(LocalDate.of(1891, 5, 15));
        authorService.add(zamyatin);
        authorService.add(bulgakov);

        Book we = new Book();
        we.setTitle("We");
        we.setAuthor(zamyatin);
        we.setGenre(dystopia);
        Book theMasterAndMargarita = new Book();
        theMasterAndMargarita.setTitle("The Master and Margarita");
        theMasterAndMargarita.setAuthor(bulgakov);
        theMasterAndMargarita.setGenre(satire);
        bookService.add(we);
        bookService.add(theMasterAndMargarita);

        System.out.println(bookService.getByTitle("We"));
        bookService.getAllBooksByAuthor(bulgakov).forEach(System.out::println);
        bookService.getAllBooksByGenre(dystopia).forEach(System.out::println);
    }
}

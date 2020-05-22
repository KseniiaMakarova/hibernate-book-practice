package com.book.practice;

import com.book.practice.lib.Injector;
import com.book.practice.model.Author;
import com.book.practice.model.Book;
import com.book.practice.model.Genre;
import com.book.practice.service.AuthorService;
import com.book.practice.service.BookService;
import com.book.practice.service.GenreService;
import java.time.LocalDate;
import java.util.List;

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

        Author ilf = new Author();
        ilf.setName("Ilya Ilf");
        ilf.setDateOfBirth(LocalDate.of(1897, 10, 15));

        Author petrov = new Author();
        petrov.setName("Yevgeny Petrov");
        petrov.setDateOfBirth(LocalDate.of(1902, 12, 13));

        authorService.add(zamyatin);
        authorService.add(bulgakov);
        authorService.add(ilf);
        authorService.add(petrov);

        Book we = new Book();
        we.setTitle("We");
        we.setAuthors(List.of(zamyatin));
        we.setGenre(dystopia);

        Book theMasterAndMargarita = new Book();
        theMasterAndMargarita.setTitle("The Master and Margarita");
        theMasterAndMargarita.setAuthors(List.of(bulgakov));
        theMasterAndMargarita.setGenre(satire);

        Book theTwelveChairs = new Book();
        theTwelveChairs.setTitle("The Twelve Chairs");
        theTwelveChairs.setAuthors(List.of(ilf, petrov));
        theTwelveChairs.setGenre(satire);

        bookService.add(we);
        bookService.add(theMasterAndMargarita);
        bookService.add(theTwelveChairs);
        System.out.println(bookService.getByTitle("We"));
        bookService.getAllBooksByAuthor(ilf).forEach(System.out::println);
        bookService.getAllBooksByGenre(satire).forEach(System.out::println);
    }
}

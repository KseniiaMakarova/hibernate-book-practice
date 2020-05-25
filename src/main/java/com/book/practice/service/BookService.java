package com.book.practice.service;

import com.book.practice.model.Author;
import com.book.practice.model.Book;
import com.book.practice.model.Genre;
import java.util.List;

public interface BookService {
    Book add(Book book);

    Book getByTitle(String title);

    List<Book> getAllBooksByAuthor(Author author);

    List<Book> getAllBooksByGenre(Genre genre);
}

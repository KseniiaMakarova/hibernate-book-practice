package com.book.practice.service.impl;

import com.book.practice.dao.BookDao;
import com.book.practice.lib.Inject;
import com.book.practice.lib.Service;
import com.book.practice.model.Author;
import com.book.practice.model.Book;
import com.book.practice.model.Genre;
import com.book.practice.service.BookService;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Inject
    private BookDao bookDao;

    @Override
    public Book add(Book book) {
        return bookDao.add(book);
    }

    @Override
    public Book getByTitle(String title) {
        return bookDao.getByTitle(title);
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        return bookDao.getAllBooksByAuthor(author);
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        return bookDao.getAllBooksByGenre(genre);
    }
}

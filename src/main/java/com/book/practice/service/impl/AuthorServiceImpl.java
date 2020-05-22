package com.book.practice.service.impl;

import com.book.practice.dao.AuthorDao;
import com.book.practice.lib.Inject;
import com.book.practice.lib.Service;
import com.book.practice.model.Author;
import com.book.practice.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Inject
    private AuthorDao authorDao;

    @Override
    public Author add(Author author) {
        return authorDao.add(author);
    }
}

package com.book.practice.service.impl;

import com.book.practice.dao.GenreDao;
import com.book.practice.lib.Inject;
import com.book.practice.lib.Service;
import com.book.practice.model.Genre;
import com.book.practice.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
    @Inject
    private GenreDao genreDao;

    @Override
    public Genre add(Genre genre) {
        return genreDao.add(genre);
    }
}

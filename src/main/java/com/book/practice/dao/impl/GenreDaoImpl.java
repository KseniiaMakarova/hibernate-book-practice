package com.book.practice.dao.impl;

import com.book.practice.dao.GenreDao;
import com.book.practice.exception.DataProcessingException;
import com.book.practice.lib.Dao;
import com.book.practice.model.Genre;
import com.book.practice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class GenreDaoImpl implements GenreDao {
    @Override
    public Genre add(Genre genre) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(genre);
            transaction.commit();
            return genre;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting "
                    + genre, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

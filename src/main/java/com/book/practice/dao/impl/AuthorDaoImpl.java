package com.book.practice.dao.impl;

import com.book.practice.dao.AuthorDao;
import com.book.practice.exception.DataProcessingException;
import com.book.practice.lib.Dao;
import com.book.practice.model.Author;
import com.book.practice.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class AuthorDaoImpl implements AuthorDao {
    @Override
    public Author add(Author author) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(author);
            transaction.commit();
            return author;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting "
                    + author, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}

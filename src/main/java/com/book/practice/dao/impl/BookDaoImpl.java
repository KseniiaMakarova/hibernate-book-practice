package com.book.practice.dao.impl;

import com.book.practice.dao.BookDao;
import com.book.practice.exception.DataProcessingException;
import com.book.practice.lib.Dao;
import com.book.practice.model.Author;
import com.book.practice.model.Book;
import com.book.practice.model.Genre;
import com.book.practice.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class BookDaoImpl implements BookDao {
    @Override
    public Book add(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("There was an error inserting "
                    + book, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Book getByTitle(String title) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            root.fetch("authors", JoinType.INNER);
            Predicate predicate = criteriaBuilder.equal(root.get("title"), title);
            return session.createQuery(query.where(predicate)).getSingleResult();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }

    @Override
    public List<Book> getAllBooksByAuthor(Author author) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            root.fetch("authors", JoinType.INNER);
            Predicate predicate = criteriaBuilder.isMember(author, root.get("authors"));
            return session.createQuery(query.where(predicate)).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }

    @Override
    public List<Book> getAllBooksByGenre(Genre genre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = query.from(Book.class);
            root.fetch("authors", JoinType.INNER);
            Predicate predicate = criteriaBuilder.equal(root.get("genre"), genre);
            return session.createQuery(query.where(predicate)).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    "There was an error retrieving the book", e);
        }
    }
}

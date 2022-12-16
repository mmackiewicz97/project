package org.book.dao;

import org.book.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;

import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    //automatyczne wykorzystanie beana sessionFactory
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
//pobranie i zwrocenie wszystkich ksiazek z bazy za pomoca zapytania SQL:
    public List<Book> getBooks() {
        //sesja hibertabe
        Session currentSession = sessionFactory.getCurrentSession();
        //zapytanie
        Query<Book> query = currentSession.createQuery("FROM Book", Book.class);
        List<Book> books = query.getResultList();
        return books ;
    }

    @Override
    public void saveBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(book);
    }

    @Override
    public Book getBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Book.class, bookId);
    }

    @Override
    public void deleteBook(int bookId) {
        Session session = sessionFactory.getCurrentSession();
//        Book book = session.get(Book.class, bookId);
        session.delete(getBook(bookId));
    }
//    @Override
//    public void deleteBook(int bookId) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("DELETE FROM Book WHERE id = :id");
//        query.setParameter("id", bookId);
//        query.executeUpdate();
//    }
}
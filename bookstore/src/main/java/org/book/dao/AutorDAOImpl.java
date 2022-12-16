package org.book.dao;

import org.book.entity.Autor;
import org.book.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AutorDAOImpl implements AutorDAO{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<Autor> getAutors() {
        Session currentSession = sessionFactory.getCurrentSession();
        //zapytanie
        Query<Autor> query = currentSession.createQuery("FROM Autor", Autor.class);
        List<Autor> autors = query.getResultList();
        return autors;
    }

    @Override
    public void saveAutor(Autor autor) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(autor);
    }

    @Override
    public Autor getAutor(int autorId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Autor.class, autorId);
    }

    @Override
    public void deleteAutor(int autorId) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getAutor(autorId));
    }
}

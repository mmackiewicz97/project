package org.book.dao;


import org.book.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO {
    private final SessionFactory sessionFactory;
    @Autowired
    public UserDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
    @Override
    public User getUser(String userId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("from User where id=:id", User.class)
                .setParameter("id", userId);
        return query.uniqueResult();
    }

    @Override
    public User getUserName(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<User> query = currentSession.createQuery("from User where username=:name", User.class)
                .setParameter("name", username);
        return query.getSingleResult();
    }
}
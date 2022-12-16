package org.book.dao;

import org.book.entity.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDAOImpl implements ShoppingCartDAO{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public void save(ShoppingCart shoppingCart) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(shoppingCart);
    }
}

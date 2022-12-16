package org.book.dao;

import org.book.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CartItemDAOImpl implements CartItemDAO{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    @Override
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {
        Session session = sessionFactory.getCurrentSession();
        Query<CartItem> query = session.createQuery("from CartItem where shoppingCart=:id", CartItem.class)
                .setParameter("id", shoppingCart);

        return query.getResultList();
    }

    @Override
    public void save(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(cartItem);
    }

    @Override
    public CartItem findById(int cartItemId) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(CartItem.class, cartItemId);
    }

    @Override
    public void delete(CartItem cartItem) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(cartItem);
    }
}

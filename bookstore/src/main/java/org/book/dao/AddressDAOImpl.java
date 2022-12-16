package org.book.dao;

import org.book.entity.ShippingAddress;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AddressDAOImpl implements AddressDAO{
    private SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void saveAddress(ShippingAddress address) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(address);
    }

    @Override
    public ShippingAddress getAddress(int addressId) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(ShippingAddress.class, addressId);
    }

    @Override
    public void deleteAddress(int addressId) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.delete(getAddress(addressId));
    }
}

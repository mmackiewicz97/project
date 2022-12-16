package org.book.services;

import org.book.dao.AddressDAO;
import org.book.dao.AutorDAO;
import org.book.entity.ShippingAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService{
    @Autowired
    private AddressDAO addressDAO;
    @Override
    @Transactional
    public void saveAddress(ShippingAddress address) {
        addressDAO.saveAddress(address);
    }

    @Override
    @Transactional
    public ShippingAddress getAddress(int addressId) {
        return addressDAO.getAddress(addressId);
    }

    @Override
    @Transactional
    public void deleteAddress(int addressId) {
        addressDAO.deleteAddress(addressId);
    }
}

package org.book.dao;

import org.book.entity.Book;
import org.book.entity.ShippingAddress;

import java.util.List;

public interface AddressDAO {
    void saveAddress(ShippingAddress address);
    ShippingAddress getAddress(int addressId);
    void deleteAddress(int addressId);
}

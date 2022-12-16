package org.book.services;

import org.book.entity.ShippingAddress;

public interface AddressService {
    void saveAddress(ShippingAddress address);
    ShippingAddress getAddress(int addressId);
    void deleteAddress(int addressId);
}
package org.book.services;

import org.book.entity.ShoppingCart;

public interface ShoppingCartService {
    void updateShoppingCart(ShoppingCart shoppingCart);
    void save(ShoppingCart shoppingCart);
}

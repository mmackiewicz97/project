package org.book.dao;

import org.book.entity.Book;
import org.book.entity.CartItem;
import org.book.entity.ShoppingCart;
import org.book.entity.User;

import java.util.List;

public interface CartItemDAO {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);
    void save(CartItem cartItem);

    CartItem findById(int cartItemId);
    void delete(CartItem cartItem);
}

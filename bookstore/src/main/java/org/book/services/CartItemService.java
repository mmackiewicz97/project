package org.book.services;

import org.book.entity.Book;
import org.book.entity.CartItem;
import org.book.entity.ShoppingCart;
import org.book.entity.User;

import java.util.List;

public interface CartItemService {
    List<CartItem> findByShoppingCart(ShoppingCart shoppingCart);

    CartItem updateCartItem(CartItem cartItem);
    CartItem addBookToCartItem(Book book, User user, int qty);

    CartItem findById(int cartItemId);
    void delete(CartItem cartItem);
}

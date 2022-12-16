package org.book.services;

import org.book.dao.BookDAO;
import org.book.dao.CartItemDAO;
import org.book.entity.Book;
import org.book.entity.CartItem;
import org.book.entity.ShoppingCart;
import org.book.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class CartItemServiceImpl implements CartItemService{

    private CartItemDAO cartItemDAO;
    private BookDAO bookDAO;
    @Autowired
    public void setCartItemDAO(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }
    @Autowired
    public void setBookDAO(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    @Transactional
    public List<CartItem> findByShoppingCart(ShoppingCart shoppingCart) {

        return cartItemDAO.findByShoppingCart(shoppingCart);
    }

    @Override
    @Transactional
    public CartItem updateCartItem(CartItem cartItem) {
        double price = cartItem.getBook().getCena() * cartItem.getQty();
        price = Math.round(price * 100.0) / 100.0;
        cartItem.setSubtotal((float) price);
        cartItemDAO.save(cartItem);
        return cartItem;
    }

    @Override
    @Transactional
    public CartItem addBookToCartItem(Book book, User user, int qty) {
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = findByShoppingCart(shoppingCart);
        book = bookDAO.getBook(book.getId());

        for(CartItem cartItem : cartItemList) {
            if (book.getId() == cartItem.getBook().getId()) {
                cartItem.setQty(cartItem.getQty() + qty);
                cartItem.setSubtotal(book.getCena() * qty);
                cartItemDAO.save(cartItem);
                return cartItem;
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setShoppingCart(shoppingCart);
        cartItem.setBook(book);
        cartItem.setQty(qty);
        cartItem.setSubtotal(book.getCena() * qty);
//        book.setCartItem(cartItem);
        cartItemDAO.save(cartItem);
//        bookDAO.saveBook(book);
        return cartItem;
    }

    @Override
    @Transactional
    public CartItem findById(int cartItemId) {
        return cartItemDAO.findById(cartItemId);
    }

    @Override
    @Transactional
    public void delete(CartItem cartItem) { cartItemDAO.delete(cartItem);}
}


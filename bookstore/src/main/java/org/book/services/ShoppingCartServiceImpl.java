package org.book.services;

import org.book.dao.ShoppingCartDAO;
import org.book.entity.CartItem;
import org.book.entity.ShoppingCart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ShoppingCartDAO shoppingCartDAO;

    @Override
    @Transactional
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        float cartTotal = 0;
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        for (CartItem cartItem : cartItemList){
            if(cartItem.getQty() > 0 ){
                cartItemService.updateCartItem(cartItem);
                cartTotal = cartTotal + cartItem.getSubtotal();
            }
        }
        shoppingCart.setGrandTotal(cartTotal);
    }

    @Override
    @Transactional
    public void save(ShoppingCart shoppingCart) {
        shoppingCartDAO.save(shoppingCart);
    }
}

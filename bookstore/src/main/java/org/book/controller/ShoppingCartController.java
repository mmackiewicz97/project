package org.book.controller;

import org.book.entity.Book;
import org.book.entity.CartItem;
import org.book.entity.ShoppingCart;
import org.book.entity.User;
import org.book.services.BookService;
import org.book.services.CartItemService;
import org.book.services.ShoppingCartService;
import org.book.services.UserService;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
    private UserService userService;
    private CartItemService cartItemService;
    private ShoppingCartService shoppingCartService;
    private BookService bookService;
    @Autowired
    public ShoppingCartController(UserService userService, CartItemService cartItemService, ShoppingCartService shoppingCartService, BookService bookService) {
        this.userService = userService;
        this.cartItemService = cartItemService;
        this.shoppingCartService = shoppingCartService;
        this.bookService = bookService;
    }

    @RequestMapping("/cart")
    @PreAuthorize("hasAuthority('USER')")
    public String shoppingCart(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();


        shoppingCartService.updateShoppingCart(shoppingCart);
        shoppingCartService.save(shoppingCart);
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);

        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", shoppingCart);

        return "shopping/cart";
    }
    @PostMapping("/additem")
    @PreAuthorize("hasAuthority('USER')")
    public String addItem(@RequestParam("bookId") int bookId, @RequestParam("qty") String qty, Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());;

        Book book = bookService.getBook(bookId);
        if (Integer.parseInt(qty) > book.getIlosc()){
            model.addAttribute("notEnoughStock", true);
            model.addAttribute("book",book);
//            return "redirect:/books/info?bookId="+book.getId();
            return "books/info";
        }
        CartItem cartItem = cartItemService.addBookToCartItem(book, user, Integer.parseInt(qty));
        book.setIlosc(book.getIlosc()-Integer.parseInt(qty));
        bookService.saveBook(book);
        model.addAttribute("addBookSuccess", true);
        model.addAttribute("book",book);
        return "books/info";
    }
    @RequestMapping("/updatecartitem")
    public String updateShoppingCart(@RequestParam("cartItemId") int cartItemId, @RequestParam("qty") int qty, Model model, Principal principal){
//        User user = userService.findByUsername(principal.getName());
        CartItem cartItem = cartItemService.findById(cartItemId);
        cartItem.setQty(qty);
        cartItemService.updateCartItem(cartItem);
//        shoppingCartService.updateShoppingCart(user.getShoppingCart());
        return "redirect:/shoppingcart/cart";
    }
    @RequestMapping("/removeitem")
    public String removeItem(@RequestParam("cartItemId") int cartItemId) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        Book book = bookService.getBook(cartItem.getBook().getId());
        book.setIlosc(book.getIlosc()+cartItem.getQty());
        bookService.saveBook(book);
        cartItemService.delete(cartItem);
        return "redirect:/shoppingcart/cart";
    }

}

package org.book.controller;

import org.book.entity.Autor;
import org.book.entity.CartItem;
import org.book.entity.ShippingAddress;
import org.book.entity.User;
import org.book.services.AddressService;
import org.book.services.CartItemService;
import org.book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.security.Principal;

@Controller
@RequestMapping("/check")
public class CheckoutController {
    private UserService userService;
    private CartItemService cartItemService;
    private AddressService addressService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setCartItemService(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }
    @Autowired
    public void setAddressService(AddressService addressService) {
        this.addressService = addressService;
    }

    @RequestMapping("/out")
//    public String shoppingCheckout(@RequestParam("shoppingcart") int shoppingCartId, Model model, Principal principal){
    public String shoppingCheckout(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
//        if(shoppingCartId != user.getShoppingCart().getId()){
//            model.addAttribute("badShoppingCart", true);
//            return "redirect:/";
//        }
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(user.getShoppingCart());
        if(cartItemList.size() == 0) {
            model.addAttribute("emptyCart", true);
            return "forward:/shoppingcart/cart";
        }
//        for(CartItem cartItem : cartItemList){
//            if((cartItem.getBook().getIlosc() - cartItem.getQty()) < 0) {
//                model.addAttribute("notEnoughStock", true);
//                return "forward:/shoppingcart/cart";
//            }
//        }
//        if(shippingAddressList.size() == 0) {
//            model.addAttribute("emptyShippingList", true);
//        } else {
//            model.addAttribute("emptyShippingList", false);
//        }


        model.addAttribute("cartItemList", cartItemList);
        model.addAttribute("shoppingCart", user.getShoppingCart());
        model.addAttribute("shippingAddress", user.getShippingAddress());
        return "shopping/checkout";
    }
    @GetMapping("/address")
    public String addForm(Model model, Principal principal)
    {
        User user = userService.findByUsername(principal.getName());
        ShippingAddress shippingAddress;
        if(user.getShippingAddress() != null){
            shippingAddress = user.getShippingAddress();
        } else {
            shippingAddress = new ShippingAddress();
        }
        model.addAttribute("address", shippingAddress);
        return "shopping/addressform";
    }
    @PostMapping("/address")
    public String saveAddress(@ModelAttribute("address") ShippingAddress address, Principal principal)
    {
        User user = userService.findByUsername(principal.getName());
        address.setUser(user);
        addressService.saveAddress(address);
        return "redirect:/check/out";
    }
}

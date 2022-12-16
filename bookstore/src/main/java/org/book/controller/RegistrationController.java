package org.book.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.book.entity.ShoppingCart;
import org.book.services.JavaMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.book.entity.Authority;
import org.book.entity.User;
import org.book.services.UserService;

import javax.mail.MessagingException;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final JavaMailService javaMailService;
    @Autowired
    public RegistrationController(UserService userService, JavaMailService javaMailService) {
        this.userService = userService;
        this.javaMailService = javaMailService;
    }
    @GetMapping(value = {"/register", "/adminregister"})
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, Model model) {
        String validator = validate(user);
        if (!validator.isEmpty()) {
            model.addAttribute("validator", validator);
            return "register";
        }
        user.getAuthorities().add(new Authority(user, "USER"));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);

        userService.saveUser(user);
//        try {
//
////            TODO
////            delete sending email
////            javaMailService.sendMail("mateuszmackiewicz97@gmail.com", "Witamy bookstore.org", "Dziękujemy za rejestrację w portalu bookstore.org", false);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
        return "redirect:/login";
    }
    @PostMapping("/adminregister")
    public String saveAdmin(@ModelAttribute("user") User user, Model model) {
        String validator = validate(user);
        if (!validator.isEmpty()) {
            model.addAttribute("validator", validator);
            return "register";
        }
        user.getAuthorities().add(new Authority(user, "USER"));
        user.getAuthorities().add(new Authority(user, "ADMIN"));
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        user.setShoppingCart(shoppingCart);
        userService.saveUser(user);
        return "redirect:/login";
    }

    private String validate(User user) {
        if (userService.getUser(user.getUsername()) != null) {
            return "użytkownik o takiej nazwie istnieje";
        } else if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty())
        {
            return "wypełnij wszystkie pola";
        } else if (!EmailValidator.getInstance().isValid(user.getEmail())){
            return "błędny adres email";
        }
        return "";
    }
}

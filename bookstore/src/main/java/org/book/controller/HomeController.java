package org.book.controller;

import org.book.entity.User;
import org.book.services.UserService;
import org.book.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

@Controller
public class HomeController {
    private UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        if (principal != null){
            String username = principal.getName();
            User user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "index";
    }
    @RequestMapping("/success")
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {
        String role =  authResult.getAuthorities().toString();
        if(role.contains("ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
        }
        else if(role.contains("USER")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/user"));
        }
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(ModelMap model) {
        model.addAttribute("message", "Admin Page...");
        return "users/admin";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String userPage(ModelMap model) {
        model.addAttribute("message", "User page...");
        return "users/user";
    }
}
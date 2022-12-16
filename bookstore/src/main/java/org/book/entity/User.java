package org.book.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity@Table(name = "users")
public class User {
    @Id
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false, updatable = false)
    private String email;
    @Column(nullable = false)
    private Boolean enabled;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Authority> authorities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shoppingcart_id")
    private ShoppingCart shoppingCart;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShippingAddress> shippingAddress;
    @OneToMany(mappedBy = "user")
    private List<Order> order;
    public User() {
        enabled = true;
        authorities = new ArrayList<>();
    }
    public User(String username) {this();this.username = username;}
    public User(String username, String password) {this(username);this.password = password;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Boolean getEnabled() {return enabled;}
    public void setEnabled(Boolean enabled) {this.enabled = enabled;}
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public List<Authority> getAuthorities() {return authorities;}
    public void setAuthorities(List<Authority> authorities) {this.authorities = authorities;}
    public ShippingAddress getShippingAddress() {
        return shippingAddress.get(shippingAddress.size()-1);
    }
    public void setShippingAddress(List<ShippingAddress> shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }
    public List<Order> getOrder() {
        return order;
    }
    public void setOrder(List<Order> order) {
        this.order = order;
    }
}


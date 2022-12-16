package org.book.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "shoppingcart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "suma")
    private float grandTotal;

    @OneToMany(mappedBy = "shoppingCart", fetch = FetchType.LAZY)
    private List<CartItem> cartItemList;

    @OneToOne(mappedBy = "shoppingCart")
    private User user;
    public ShoppingCart(){}
    public ShoppingCart(String id) {
        this.id = Integer.parseInt(id);
    }

    public ShoppingCart(int id, float grandTotal, List<CartItem> cartItemList, User user) {
        this.id = id;
        this.grandTotal = grandTotal;
        this.cartItemList = cartItemList;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }

    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

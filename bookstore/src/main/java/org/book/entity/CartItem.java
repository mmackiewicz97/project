package org.book.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cartitem")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "qty")
    private int qty;
    @Column(name = "subtotal")
    private float subtotal;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="booktocart",
            joinColumns = @JoinColumn(name = "cartitem_id"),
            inverseJoinColumns = @JoinColumn(name = "ksiazka_id")
    )
    private List<Book> booksList;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "shoppingcart_id")
    private ShoppingCart shoppingCart;


    public CartItem(){}
    public CartItem(String id) {
        this.id = Integer.parseInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public List<Book> getBooksList() {
        return booksList;
    }
    public Book getBook(){
        return booksList.get(0);
    }
    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }
    public void setBook(Book book){
        booksList = new ArrayList<Book>();
        booksList.add(book);
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}

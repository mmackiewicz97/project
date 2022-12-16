package org.book.entity;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class ShippingAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "miejscowosc")
    private String ShippingAddressCity;
    @Column(name = "ulica")
    private String ShippingAddressStreet;
    @Column(name = "wojewodztwo")
    private String ShippingAddressState;
    @Column(name = "kodpocztowy")
    private String ShippingAddressZipcode;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne(mappedBy = "shippingAddress")
    private Order order;

    public ShippingAddress(String id) {
        this.id = Integer.parseInt(id);
    }

    public ShippingAddress() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShippingAddressCity() {
        return ShippingAddressCity;
    }

    public void setShippingAddressCity(String shippingAddressCity) {
        ShippingAddressCity = shippingAddressCity;
    }

    public String getShippingAddressStreet() {
        return ShippingAddressStreet;
    }

    public void setShippingAddressStreet(String shippingAddressStreet) {
        ShippingAddressStreet = shippingAddressStreet;
    }

    public String getShippingAddressState() {
        return ShippingAddressState;
    }

    public void setShippingAddressState(String shippingAddressState) {
        ShippingAddressState = shippingAddressState;
    }

    public String getShippingAddressZipcode() {
        return ShippingAddressZipcode;
    }

    public void setShippingAddressZipcode(String shippingAddressZipcode) {
        ShippingAddressZipcode = shippingAddressZipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
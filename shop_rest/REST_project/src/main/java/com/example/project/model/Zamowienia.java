package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Zamówienia")
public class Zamowienia { //utwórz, anuluj, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idZamówienia")
    private int id;

    public Zamowienia() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUżytkownicy")
    private Uzytkownicy uzytkownicy;

    public Uzytkownicy getUzytkownicy() {
        return uzytkownicy;
    }

    public void setUzytkownicy(Uzytkownicy uzytkownicy) {
        this.uzytkownicy = uzytkownicy;
    }

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="idProdukty")
    private Produkty produkty;

    public Produkty getProdukty() {
        return produkty;
    }

    public void setProdukty(Produkty produkty) {
        this.produkty = produkty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

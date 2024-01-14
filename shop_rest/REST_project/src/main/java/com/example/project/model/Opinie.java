package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Opinie")
public class Opinie { //dodaj, usuń, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idOpinie")
    private int id;
    @Column(name = "opis")
    private String opis;

    public Opinie() {
    }

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="idUżytkownicy")
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }


}

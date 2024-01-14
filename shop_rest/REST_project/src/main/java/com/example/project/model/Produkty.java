package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Produkty")
public class Produkty { //dodaj, usuń, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProdukty")
    private int id;
    @Column(name = "nazwa")
    private String nazwa;
    @Column(name = "cena")
    private double cena;
    @Column(name = "ilosc")
    private int ilosc;

    public Produkty() {
    }

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="idProducenci")
    private Producenci producenci;

    public Producenci getProducenci() {
        return producenci;
    }

    public void setProducenci(Producenci producenci) {
        this.producenci = producenci;
    }

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="idKategorie")
    private Kategorie kategorie;

    public Kategorie getKategorie() {
        return kategorie;
    }

    public void setKategorie(Kategorie kategorie) {
        this.kategorie = kategorie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getIlosc() {
        return ilosc;
    }

    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    @Override
    public String toString() {
        return "Produkty: " +
                "id_produktu=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", cena=" + cena +
                ", ilosc=" + ilosc +
                '}';
    }
}

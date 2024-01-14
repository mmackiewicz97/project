package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Użytkownicy")
public class Uzytkownicy { //dodaj, usuń
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idUżytkownicy")
    private int id;
    @Column(name = "login")
    private String login;
    @Column(name = "hasło")
    private String haslo;
    @Column(name = "imię")
    private String imie;
    @Column(name = "nazwisko")
    private String nazwisko;

    public Uzytkownicy() {
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAdres")
    private Adres adres;

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
}

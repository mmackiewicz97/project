package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Kategorie")
public class Kategorie { //dodaj, usuń, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idKategorie")
    private int id;
    @Column(name = "nazwa_kategorii")
    private String nazwa;

    public Kategorie() {
    }

    public Kategorie(String nazwa) {
        this.nazwa = nazwa;
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

    @Override
    public String toString() {
        return "Kategorie: " +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}

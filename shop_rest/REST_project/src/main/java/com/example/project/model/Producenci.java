package com.example.project.model;

import javax.persistence.*;

@Entity
@Table(name = "Producenci")
public class Producenci { //dodaj, usuń, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProducenci")
    private int id;
    @Column(name = "nazwa_firmy")
    private String nazwa;

    public Producenci() {}

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
}

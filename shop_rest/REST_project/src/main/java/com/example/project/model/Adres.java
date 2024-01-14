package com.example.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;

@Entity
@Table(name = "adres")
public class Adres { //przypisz, edytuj
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idAdres")
    private int id;
    @Column(name = "miejscowość")
    private String miejscowosc;
    @Column(name = "ulica")
    private String ulica;
    @Column(name = "nr_domu")
    private String numer_domu;
    @Column(name = "nr_lokalu")
    private String numer_lokalu;
    @Column(name = "kod_pocztowy")
    private String kod_pocztowy;
    @Column(name = "miasto")
    private String miasto;

    public Adres() {
    }

    public Adres(String miejscowosc, String ulica, String numer_domu, String numer_lokalu, String kod_pocztowy, String miasto) {
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.numer_domu = numer_domu;
        this.numer_lokalu = numer_lokalu;
        this.kod_pocztowy = kod_pocztowy;
        this.miasto = miasto;
    }


    //@OneToOne(mappedBy ="adres",cascade = CascadeType.ALL)
    //private Uzytkownicy uzytkownicy;

    //public Uzytkownicy getUzytkownicy() { return uzytkownicy; }

    //public void setUzytkownicy(Uzytkownicy uzytkownicy) { this.uzytkownicy = uzytkownicy; }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public void setMiejscowosc(String miejscowosc) {
        this.miejscowosc = miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNumer_domu() {
        return numer_domu;
    }

    public void setNumer_domu(String numer_domu) {
        this.numer_domu = numer_domu;
    }

    public String getNumer_lokalu() {
        return numer_lokalu;
    }

    public void setNumer_lokalu(String numer_lokalu) {
        this.numer_lokalu = numer_lokalu;
    }

    public String getKod_pocztowy() {
        return kod_pocztowy;
    }

    public void setKod_pocztowy(String kod_pocztowy) {
        this.kod_pocztowy = kod_pocztowy;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    @Override
    public String toString() {
        return "Adres: " +
                "id=" + id +
                ", miejscowosc='" + miejscowosc + '\'' +
                ", ulica='" + ulica + '\'' +
                ", numer_domu=" + numer_domu +
                ", numer_lokalu=" + numer_lokalu +
                ", kod_pocztowy='" + kod_pocztowy + '\'' +
                ", miasto='" + miasto + '\'' +
                '}';
    }
}

package org.book.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="autorzy")
public class Autor {
    public Autor() {
    }
    public Autor(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }
    public Autor(String id){
        this.id = Integer.parseInt(id);
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="imie")
    private String imie;
    @Column(name="nazwisko")
    private String nazwisko;
    @ManyToMany
    @JoinTable(
            name="autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name="autor_id"),
            inverseJoinColumns = @JoinColumn(name="ksiazka_id")
    )
    private Set<Book> ksiazki;
    public void addKsiazka(Book ksiazka) {
        if (ksiazki == null) {
            ksiazki = new HashSet<>();
        }
//ksiazka.addAutor(this);
        ksiazki.add(ksiazka);
    }
    public void removeKsiazka(Book ksiazka){
        if (ksiazki == null)
            return;
//autor.addKsiazka(this);
        ksiazki.remove(ksiazka);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getImie() {
        return imie;
    }
    public void setImie(String imie) {
        this.imie = imie;
    }public String getNazwisko() {
        return nazwisko;
    }
    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    public Set<Book> getKsiazki() {
        return ksiazki;
    }
    public void setKsiazki(Set<Book> ksiazki) {
        this.ksiazki = ksiazki;
    }
    public String getFullName(){
        return imie + " " + nazwisko;
    }
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
}
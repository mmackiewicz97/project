package org.book.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="kategorie")
public class Category {
    public Category() {
    }
    public Category(String id) {
        this.id = Integer.parseInt(id);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nazwa")
    private String nazwa;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "kategoria",cascade = CascadeType.ALL)
    private List<Book> ksiazki;
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
    public List<Book> getKsiazki() {
        return ksiazki;
    }
    public void setKsiazki(List<Book> ksiazki) {
        this.ksiazki = ksiazki;
    }
    public void addKsiazka(Book ksiazka){
        if (ksiazki.isEmpty())
            ksiazki = new ArrayList<>();
        ksiazka.setKategoria(this);
        ksiazki.add(ksiazka);
    }
    @Override
    public String toString() {
        return "Kategoria{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                '}';
    }
}
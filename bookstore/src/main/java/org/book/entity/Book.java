package org.book.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.*;

@Entity
@Table(name="ksiazki")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="nazwa")
    private String nazwa;
    @Column(name="wydawnictwo")
    private String wydawnictwo;
    @Column(name="cena")
    private float cena;
    @Column(name="ilosc")
    private int ilosc;
    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name="kategoria_id")
    private Category kategoria;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="autorzy_to_ksiazki",
            joinColumns = @JoinColumn(name="ksiazka_id"),
            inverseJoinColumns = @JoinColumn(name="autor_id")
    )
    private Set<Autor> autorzy;

    @Column(name = "okladka")
    @Lob
    private byte[] okladka;
    @Column(name="filename")
    private String filename;
    @Column(name="content_type")
    private String contentType;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="booktocart",
            joinColumns = @JoinColumn(name = "ksiazka_id"),
            inverseJoinColumns = @JoinColumn(name = "cartitem_id")
    )
    private List<CartItem> cartItemList;


    public Book(){}
    public Book(int id) {
        this.id = id;
    }
    public Book(String nazwa, String wydawnictwo, float cena) {
        this.nazwa = nazwa;
        this.wydawnictwo = wydawnictwo;
        this.cena = cena;
    }

    public void addAutor(Autor autor){
        if (autorzy == null)
            autorzy = new HashSet<>();
//autor.addKsiazka(this);
        autorzy.add(autor);
    }
    public void removeAutor(Autor autor){
        if (autorzy == null)
            return;
//autor.addKsiazka(this);
        autorzy.remove(autor);
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
    public String getWydawnictwo() {
        return wydawnictwo;
    }
    public void setWydawnictwo(String wydawnictwo) {
        this.wydawnictwo = wydawnictwo;
    }
    public float getCena() {
        return cena;
    }public void setCena(float cena) {
        this.cena = cena;
    }
    public Category getKategoria() {
        return kategoria;
    }
    public void setKategoria(Category kategoria) {
        this.kategoria = kategoria;
    }
    public Set<Autor> getAutorzy() {
        return autorzy;
    }
    public void setAutorzy(Set<Autor> autorzy) {
        this.autorzy = autorzy;
    }
    public int getIlosc() {
        return ilosc;
    }
    public void setIlosc(int ilosc) {
        this.ilosc = ilosc;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOkladka() {
        if(okladka==null){
            okladka = new byte[0];
        }
//        byte[] encoded=org.apache.commons.codec.binary.Base64
//                .encodeBase64(okladka);
        String encodedString = Base64.getEncoder().encodeToString(okladka);
//        String encodedString = new String(encoded);
        return encodedString;
    }

    public void setOkladka(byte[] okladka) {
        this.okladka = okladka;
    }

    public List<CartItem> getCartItemList() {
        return cartItemList;
    }
    public CartItem getCartItem(){return cartItemList.get(0);}
    public void setCartItemList(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }
    public void setCartItem(CartItem cartItem){
        cartItemList = new ArrayList<CartItem>();
        cartItemList.add(cartItem);
    }
    @Override
    public String toString() {
        return "Ksiazka{" +
                "id=" + id +
                ", nazwa='" + nazwa + '\'' +
                ", wydawnictwo='" + wydawnictwo + '\'' +
                ", cena=" + cena +
                ", kategoria=" + kategoria +
                '}';
    }
}

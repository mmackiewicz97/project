package com.example.project.service;

import com.example.project.model.Kategorie;
import com.example.project.model.Zamowienia;

import java.util.List;

public interface ZamowieniaService {

    Zamowienia get(int id);

    List<Zamowienia> getAll();

    public Zamowienia create(Zamowienia zamowienia);

    public Zamowienia update(Zamowienia zamowienia, int id);

    public void delete(int id);
}

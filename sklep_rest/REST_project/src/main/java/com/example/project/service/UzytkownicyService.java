package com.example.project.service;


import com.example.project.model.Uzytkownicy;

import java.util.List;

public interface UzytkownicyService {

    List<Uzytkownicy> getAll();

    Uzytkownicy get(int id);

    public Uzytkownicy create(Uzytkownicy uzytkownicy);

    public void delete(int id);
}

package com.example.project.service;
import java.util.List;
import com.example.project.model.Kategorie;

public interface KategorieService {
     Kategorie get(int id);

     List<Kategorie> getAll();

     Kategorie create(Kategorie kategorie);

     void delete(int id);

    Kategorie updatePost(Kategorie kategorie, int id);
}

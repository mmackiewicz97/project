package com.example.project.service;

import com.example.project.model.Kategorie;
import com.example.project.model.Producenci;

import java.util.List;

public interface ProducenciService {

    Producenci get(int id);

    List<Producenci> getAll();

    public Producenci create(Producenci producenci);

    public Producenci updatePost(Producenci producenci, int id);

    public void delete(int id);
}

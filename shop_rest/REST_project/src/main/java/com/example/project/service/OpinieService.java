package com.example.project.service;

import com.example.project.model.Opinie;
import com.example.project.model.Produkty;

import java.util.List;

public interface OpinieService {
    public Opinie get(int id);

    public List<Opinie> getAll();

    public Opinie create(Opinie opinie);

    public Opinie update(Opinie opinie, int id);

    public void delete(int id);
}

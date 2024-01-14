package com.example.project.service;

import com.example.project.model.Produkty;

import java.time.Period;
import java.util.List;

public interface ProduktyService {

    public List<Produkty> getAll();

    public Produkty get(int id);

    public Produkty create(Produkty produkty);

    public Produkty update(Produkty produkty, int id);

    public void delete(int id);

}

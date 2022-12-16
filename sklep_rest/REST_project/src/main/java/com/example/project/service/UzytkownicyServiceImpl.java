package com.example.project.service;

import com.example.project.ObjectNotFoundException;
import com.example.project.model.Producenci;
import com.example.project.model.Produkty;
import com.example.project.model.Uzytkownicy;
import com.example.project.repository.ProduktyRepository;
import com.example.project.repository.UzytkownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UzytkownicyServiceImpl implements UzytkownicyService {

    @Autowired
    UzytkownicyRepository repository;

    @Override
    public List<Uzytkownicy> getAll() {
        return repository.findAll();
    }

    @Override
    public Uzytkownicy get(int id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Nie można znaleźć uzytkownika o id: "));
    }
    @Override
    public Uzytkownicy create(Uzytkownicy uzytkownicy)  {
        return repository.save(uzytkownicy);
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Użytkownik usunięty!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono uzytkownika o id: ");
        }

    }
}

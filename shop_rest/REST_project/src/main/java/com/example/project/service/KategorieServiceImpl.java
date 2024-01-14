package com.example.project.service;

import com.example.project.KategorieNotFoundException;
import com.example.project.ObjectNotFoundException;
import com.example.project.model.Kategorie;
import com.example.project.model.Opinie;
import com.example.project.repository.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class KategorieServiceImpl implements KategorieService {

    @Autowired
    KategorieRepository repository;

    @Override
    public Kategorie get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Nie zaleziono kategorii o id: "));
    }

    @Override
    public List<Kategorie> getAll() {
        return repository.findAll();
    }

    @Override
    public Kategorie create(Kategorie kategorie) {
        return repository.save(kategorie);
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Kategoria usunięta!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono kategorii o id: ");
        }
    }

    @Override
    public Kategorie updatePost(Kategorie kategorie, int id) {
        return repository.findById(id)
                .map(category -> {
                    category.setNazwa(kategorie.getNazwa());
                    return repository.save(category);
                })
                .orElseGet(() ->{
                    kategorie.setId(id);
                    repository.flush();
                    return repository.save(kategorie);
                });
    }
}

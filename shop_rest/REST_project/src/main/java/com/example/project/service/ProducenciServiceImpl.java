package com.example.project.service;

import com.example.project.KategorieNotFoundException;
import com.example.project.ObjectNotFoundException;
import com.example.project.model.Kategorie;
import com.example.project.model.Producenci;
import com.example.project.model.Produkty;
import com.example.project.repository.ProducenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProducenciServiceImpl implements ProducenciService {

    @Autowired
    ProducenciRepository repository;

    @Override
    public Producenci get(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, "Nie można znaleźć producenta o id: "));
    }

    @Override
    public List<Producenci> getAll(){return  repository.findAll();}

    @Override
    public Producenci create(Producenci producenci) {return repository.save(producenci);
    }

    @Override
    public Producenci updatePost(Producenci producenci, int id) {
        return repository.findById(id)
                .map(producent -> {
                    producent.setNazwa(producenci.getNazwa());
                    return repository.save(producent);
                })
                .orElseGet(() ->{
                    producenci.setId(id);
                    repository.flush();
                    return repository.save(producenci);
                });
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Producent usunięty!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono producenta o id: ");
        }
    }
}

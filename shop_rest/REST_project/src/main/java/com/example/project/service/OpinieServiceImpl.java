package com.example.project.service;

import com.example.project.ObjectNotFoundException;
import com.example.project.model.Opinie;
import com.example.project.model.Producenci;
import com.example.project.repository.KategorieRepository;
import com.example.project.repository.OpinieRepository;
import com.example.project.repository.UzytkownicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OpinieServiceImpl implements OpinieService {

    @Autowired
    OpinieRepository repository;
    @Autowired
    UzytkownicyService uzytkownicyService;

    @Override
    public Opinie get(int id){return repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id, "Nie można znaleźć opinii o id: "));}

    public List<Opinie> getAll(){return repository.findAll();}

    @Override
    public Opinie create(Opinie opinie) {

        opinie.setUzytkownicy(uzytkownicyService.get(opinie.getUzytkownicy().getId()));
        return repository.save(opinie);
    }

    @Override
    public Opinie update(Opinie opinie, int id) {
        return repository.findById(id)
                .map(opinia -> {
                    opinia.setOpis(opinie.getOpis());
                    return repository.save(opinia);
                })
                .orElseGet(() ->{
                    opinie.setId(id);
                    repository.flush();
                    return repository.save(opinie);
                });
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Opinia usunięta!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono opinii o id: ");
        }
    }
}

package com.example.project.service;

import com.example.project.ObjectNotFoundException;
import com.example.project.model.Kategorie;
import com.example.project.model.Produkty;
import com.example.project.model.Uzytkownicy;
import com.example.project.model.Zamowienia;
import com.example.project.repository.ProduktyRepository;
import com.example.project.repository.ZamowieniaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ZamowieniaServiceImpl implements ZamowieniaService {

    @Autowired
    ZamowieniaRepository repository;
    @Autowired
    ProduktyService produktyService;
    @Autowired
    UzytkownicyService uzytkownicyService;

    @Override
    public List<Zamowienia> getAll() {
        return repository.findAll();
    }

    @Override
    public Zamowienia get(int id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, "Nie można znaleźć zamówienia o id: "));
    }

    @Override
    public Zamowienia create(Zamowienia zamowienia) {
        zamowienia.setUzytkownicy(uzytkownicyService.get(zamowienia.getUzytkownicy().getId()));
        zamowienia.setProdukty(produktyService.get(zamowienia.getProdukty().getId()));
         return repository.save(zamowienia);
    }


    @Override
    public Zamowienia update(Zamowienia zamowienia, int id) {
        return repository.findById(id)
                .map(zamowienie -> {
                    zamowienie.setUzytkownicy(uzytkownicyService.get(zamowienia.getUzytkownicy().getId()));
                    zamowienie.setProdukty(produktyService.get(zamowienia.getProdukty().getId()));
                    return repository.save(zamowienie);
                })
                .orElseGet(() ->{
                    zamowienia.setId(id);
                    repository.flush();
                    return repository.save(zamowienia);
                });
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Zamowienie usunięte!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono zamowienia o id: ");
        }
    }
}

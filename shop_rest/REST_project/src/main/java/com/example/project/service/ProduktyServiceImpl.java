package com.example.project.service;

import com.example.project.ObjectNotFoundException;
import com.example.project.model.Producenci;
import com.example.project.model.Produkty;
import com.example.project.repository.ProducenciRepository;
import com.example.project.repository.ProduktyRepository;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProduktyServiceImpl implements ProduktyService {

    @Autowired
    ProduktyRepository repository;
    @Autowired
    ProducenciService producenciService;
    @Autowired
    KategorieService kategorieService;

    @Override
    public List<Produkty> getAll() {
        return (List<Produkty>) repository.findAll();
    }

    @Override
    public Produkty get(int id) {
        return repository.findById(id).orElseThrow(()->new ObjectNotFoundException(id, "Brak produktu o id: "));
    }

    @Override
    public Produkty create(Produkty produkty) {
        produkty.setProducenci(producenciService.get(produkty.getProducenci().getId()));
        produkty.setKategorie(kategorieService.get(produkty.getKategorie().getId()));
        return repository.save(produkty);
    }

    @Override
    public Produkty update(Produkty produkty, int id) {
        return repository.findById(id).map(produkt ->{
            produkt.setNazwa(produkty.getNazwa());
            produkt.setCena(produkty.getCena());
            produkt.setIlosc(produkty.getIlosc());
            produkt.setProducenci(produkty.getProducenci());
            produkt.setKategorie(produkty.getKategorie());
            return repository.save(produkt);
        }).orElseGet(()->{
            produkty.setId(id);
            repository.flush();
            return  repository.save(produkty);
        });

//        Produkty istniejacyProdukt = repository.getById(produkty.getId_produktu());
//        istniejacyProdukt.setNazwa(produkty.getNazwa());
//        istniejacyProdukt.setCena(produkty.getCena());
//        istniejacyProdukt.setIlosc(produkty.getIlosc());
//        istniejacyProdukt.setProducenci(produkty.getProducenci());
//        istniejacyProdukt.setKategorie(produkty.getKategorie());
//        repository.save(istniejacyProdukt);
    }

    @Override
    public void delete(int id) {
        try{
            repository.deleteById(id);
            System.out.println("Produkt usunięty!!" + id);
        }
        catch (Exception exception){
            throw new ObjectNotFoundException(id, "Nie znaleziono produktu o id: ");
        }
    }
}

package com.example.project.service;

import com.example.project.model.Adres;
import com.example.project.model.Kategorie;
import com.example.project.repository.AdresRepository;
import com.example.project.repository.KategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdresServiceImpl implements AdresService {

    @Autowired
    AdresRepository repository;

    @Override
    public void create(Adres adres) {
        repository.save(adres);
    }

    @Override
    public void update(Adres adres) {
        Adres istniejacyAdres = repository.getById(adres.getId());
        istniejacyAdres.setMiejscowosc(adres.getMiejscowosc());
        istniejacyAdres.setUlica(adres.getUlica());
        istniejacyAdres.setNumer_domu(adres.getNumer_domu());
        istniejacyAdres.setNumer_lokalu(adres.getNumer_lokalu());
        istniejacyAdres.setKod_pocztowy(adres.getKod_pocztowy());
        istniejacyAdres.setMiasto(adres.getMiasto());
        repository.save(istniejacyAdres);
    }
}

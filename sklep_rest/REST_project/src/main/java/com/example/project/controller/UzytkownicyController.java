package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Uzytkownicy;
import com.example.project.service.UzytkownicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/uzytkownicy")
public class UzytkownicyController {

    @Autowired
    UzytkownicyService service;

    @GetMapping("/{id}")
        public EntityModel<Uzytkownicy> get(@PathVariable int id){
        Uzytkownicy uzytkownik = service.get(id);
        return EntityModel.of(uzytkownik, //
                linkTo(methodOn(UzytkownicyController.class).get(id)).withSelfRel(),
                linkTo(methodOn(UzytkownicyController.class).getAll()).withRel("uzytkownicy"));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Uzytkownicy>> getAll(){
       List<EntityModel<Uzytkownicy>> uzytkownicy = service.getAll().stream()
                .map(uzytkownik -> EntityModel.of(uzytkownik,
                        linkTo(methodOn(UzytkownicyController.class).get(uzytkownik.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());
        return CollectionModel.of(uzytkownicy,
                linkTo(methodOn(UzytkownicyController.class).getAll()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Uzytkownicy uzytkownicy) {
        Uzytkownicy uzytkownik = service.create(uzytkownicy);
        EntityModel<Uzytkownicy> uzytkownikModel = EntityModel.of(uzytkownik, //
                linkTo(methodOn(UzytkownicyController.class).get(uzytkownik.getId())).withSelfRel(),
                linkTo(methodOn(UzytkownicyController.class).getAll()).withRel("kategorie"));
        return ResponseEntity.created(uzytkownikModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(uzytkownikModel);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Delete sucesfully id = " + id;
    }
}

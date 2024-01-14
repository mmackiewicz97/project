package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Produkty;
import com.example.project.repository.ProduktyRepository;
import com.example.project.service.ProduktyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
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
@RequestMapping(value = "/produkty")
public class ProduktyController {

    @Autowired
    ProduktyService service;

    @GetMapping("/{id}")
    public EntityModel<Produkty> get(@PathVariable int id){
        Produkty produkt = service.get(id);
        return EntityModel.of(produkt, //
                linkTo(methodOn(ProduktyController.class).get(id)).withSelfRel(),
                linkTo(methodOn(ProduktyController.class).getAll()).withRel("produkty"));
    }
    @GetMapping()
    public CollectionModel<EntityModel<Produkty>> getAll(){

        List<EntityModel<Produkty>> produkty = service.getAll().stream()
                .map(produkt -> EntityModel.of(produkt,
                        linkTo(methodOn(ProduktyController.class).get(produkt.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());
        return CollectionModel.of(produkty,
                linkTo(methodOn(ProduktyController.class).getAll()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Produkty produkty) {
        Produkty produkt = service.create(produkty);
        EntityModel<Produkty> produktyModel = EntityModel.of(produkt, //
                linkTo(methodOn(ProduktyController.class).get(produkt.getId())).withSelfRel(),
                linkTo(methodOn(ProduktyController.class).getAll()).withRel("produkty"));
        return ResponseEntity.created(produktyModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(produktyModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Produkty produkty, @PathVariable int id) {
        Produkty produkt = service.update(produkty, id);
        EntityModel<Produkty> produktyModel = EntityModel.of(produkt, //
                linkTo(methodOn(ProduktyController.class).get(produkt.getId())).withSelfRel(),
                linkTo(methodOn(ProduktyController.class).getAll()).withRel("produkty"));
        return ResponseEntity.created(produktyModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(produktyModel);
    }

    @DeleteMapping("/{id}")
    public String deleteProdukty(@PathVariable int id) {
        service.delete(id);
        return "Delete sucesfully id = " + id;
    }
}

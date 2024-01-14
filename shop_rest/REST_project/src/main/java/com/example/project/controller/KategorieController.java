package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Opinie;
import com.example.project.service.KategorieService;
import com.example.project.service.OpinieService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/kategorie")
public class KategorieController {
    @Autowired
    KategorieService service;

    @GetMapping("/{id}")
    public EntityModel<Kategorie> get(@PathVariable int id){
        Kategorie kategoria = service.get(id);
        return EntityModel.of(kategoria, //
                linkTo(methodOn(KategorieController.class).get(id)).withSelfRel(),
                linkTo(methodOn(KategorieController.class).getAll()).withRel("kategorie"));
    }
    @GetMapping()
    public CollectionModel<EntityModel<Kategorie>> getAll(){

        List<EntityModel<Kategorie>> kategorie = service.getAll().stream()
                .map(kategoria -> EntityModel.of(kategoria,
                        linkTo(methodOn(KategorieController.class).get(kategoria.getId())).withSelfRel()
//                        linkTo(methodOn(KategorieController.class).getAll()).withRel("kategorie")
                ))
                .collect(Collectors.toList());
        return CollectionModel.of(kategorie,
                linkTo(methodOn(KategorieController.class).getAll()).withSelfRel());
    }
    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Kategorie kategorie) {
        Kategorie kategoria = service.create(kategorie);
        EntityModel<Kategorie> kategoriaModel = EntityModel.of(kategoria, //
                linkTo(methodOn(KategorieController.class).get(kategoria.getId())).withSelfRel(),
                linkTo(methodOn(KategorieController.class).getAll()).withRel("kategorie"));
        return ResponseEntity.created(kategoriaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(kategoriaModel);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Kategorie kategorie, @PathVariable int id) {
        Kategorie kategoria = service.updatePost(kategorie, id);
        EntityModel<Kategorie> kategoriaModel = EntityModel.of(kategoria, //
                linkTo(methodOn(KategorieController.class).get(kategoria.getId())).withSelfRel(),
                linkTo(methodOn(KategorieController.class).getAll()).withRel("kategorie"));
        return ResponseEntity.created(kategoriaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(kategoriaModel);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable (value = "id") int id) {
        service.delete(id);
        return "Usunięta kategoria id: " + id;
    }
}

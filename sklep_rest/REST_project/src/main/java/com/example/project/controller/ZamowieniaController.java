package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Opinie;
import com.example.project.model.Uzytkownicy;
import com.example.project.model.Zamowienia;
import com.example.project.service.OpinieService;
import com.example.project.service.ZamowieniaService;
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
@RequestMapping(value = "/zamowienia")
public class ZamowieniaController {
    @Autowired
    ZamowieniaService service;

    @GetMapping("/{id}")
    public EntityModel<Zamowienia> get(@PathVariable int id){
        Zamowienia zamowienia = service.get(id);
        return EntityModel.of(zamowienia, //
                linkTo(methodOn(ZamowieniaController.class).get(id)).withSelfRel(),
                linkTo(methodOn(ZamowieniaController.class).getAll()).withRel("zamowienia"));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Zamowienia>> getAll(){
        List<EntityModel<Zamowienia>> zamowienia = service.getAll().stream()
                .map(zamowienie -> EntityModel.of(zamowienie,
                        linkTo(methodOn(ZamowieniaController.class).get(zamowienie.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());
        return CollectionModel.of(zamowienia,
                linkTo(methodOn(ZamowieniaController.class).getAll()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Zamowienia zamowienia) {
        Zamowienia zamowienie = service.create(zamowienia);
        EntityModel<Zamowienia> zamowieniaModel = EntityModel.of(zamowienie, //
                linkTo(methodOn(ZamowieniaController.class).get(zamowienie.getId())).withSelfRel(),
                linkTo(methodOn(ZamowieniaController.class).getAll()).withRel("zamowienia"));
        return ResponseEntity.created(zamowieniaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(zamowieniaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Zamowienia zamowienia, @PathVariable int id) {
        Zamowienia zamowienie = service.update(zamowienia, id);
        EntityModel<Zamowienia> zamowieniaModel = EntityModel.of(zamowienie, //
                linkTo(methodOn(ZamowieniaController.class).get(zamowienie.getId())).withSelfRel(),
                linkTo(methodOn(ZamowieniaController.class).getAll()).withRel("zamowienia"));
        return ResponseEntity.created(zamowieniaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(zamowieniaModel);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable int id) {
        service.delete(id);
        return "Delete sucesfully id = " + id;
    }
}

package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Opinie;
import com.example.project.model.Producenci;
import com.example.project.service.OpinieService;
import com.example.project.service.ProducenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@EnableAutoConfiguration
@RequestMapping(value = "/opinie")
public class OpinieController {
    @Autowired
    OpinieService service;

    @GetMapping("/{id}")
    public EntityModel<Opinie> get(@PathVariable int id){
        Opinie opinia = service.get(id);
        return EntityModel.of(opinia, //
                linkTo(methodOn(OpinieController.class).get(id)).withSelfRel(),
                linkTo(methodOn(OpinieController.class).getAll()).withRel("opinie"));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Opinie>> getAll(){

        List<EntityModel<Opinie>> opinie = service.getAll().stream()
                .map(opinia -> EntityModel.of(opinia,
                        linkTo(methodOn(KategorieController.class).get(opinia.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());
        return CollectionModel.of(opinie,
                linkTo(methodOn(OpinieController.class).getAll()).withSelfRel());
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Opinie opinie) {
        Opinie opinia = service.create(opinie);
        EntityModel<Opinie> opiniaModel = EntityModel.of(opinia, //
                linkTo(methodOn(OpinieController.class).get(opinia.getId())).withSelfRel(),
                linkTo(methodOn(OpinieController.class).getAll()).withRel("opinie"));
        return ResponseEntity.created(opiniaModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(opiniaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Opinie opinie,@PathVariable int id) {
        Opinie opinia = service.update(opinie, id);
        EntityModel<Opinie> opiniaModel = EntityModel.of(opinia,
                linkTo(methodOn(OpinieController.class).get(opinia.getId())).withSelfRel(),
                linkTo(methodOn(OpinieController.class).getAll()).withRel("opinie"));
        return ResponseEntity.created(opiniaModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(opiniaModel);
    }

    @DeleteMapping("/{id}")
    public String deleteOpinie(@PathVariable (value = "id") int id) {
        service.delete(id);
        return "Delete sucesfully id = " + id;
    }
}

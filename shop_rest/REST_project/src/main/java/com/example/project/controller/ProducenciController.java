package com.example.project.controller;

import com.example.project.model.Kategorie;
import com.example.project.model.Producenci;
import com.example.project.model.Produkty;
import com.example.project.service.ProducenciService;
import com.example.project.service.ProduktyService;
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
@RequestMapping(value = "/producenci")
public class ProducenciController {

    @Autowired
    ProducenciService service;

    @GetMapping("/{id}")
    public EntityModel<Producenci> get(@PathVariable int id){
        Producenci producent = service.get(id);
        return EntityModel.of(producent, linkTo(methodOn(ProducenciController.class).get(id)).withSelfRel(),
                linkTo(methodOn(ProducenciController.class).getAll()).withRel("producenci"));
    }

    @GetMapping()
    public CollectionModel<EntityModel<Producenci>> getAll(){
        List<EntityModel<Producenci>> producenci = service.getAll().stream()
                .map(producent -> EntityModel.of(producent,linkTo(methodOn(ProducenciController.class).get(producent.getId())).withSelfRel())).collect(Collectors.toList());
        return CollectionModel.of(producenci,linkTo(methodOn(ProducenciController.class).getAll()).withSelfRel());
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody Producenci producenci) {
        Producenci producent = service.create(producenci);

        EntityModel<Producenci> producentModel = EntityModel.of(producent, //
                linkTo(methodOn(ProducenciController.class).get(producent.getId())).withSelfRel(),
                linkTo(methodOn(ProducenciController.class).getAll()).withRel("producenci"));
        return ResponseEntity.created(producentModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(producentModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Producenci producenci, @PathVariable int id) {
        Producenci producent = service.updatePost(producenci, id);
        EntityModel<Producenci> producenciModel = EntityModel.of(producent, //
                linkTo(methodOn(ProducenciController.class).get(producent.getId())).withSelfRel(),
                linkTo(methodOn(ProducenciController.class).getAll()).withRel("producenci"));
        return ResponseEntity.created(producenciModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(producenciModel);
    }

    @DeleteMapping("/{id}")
    public String deleteProducenci(@PathVariable (value = "id") int id) {
        service.delete(id);
        return "Usunięty producent id = " + id;
    }
}

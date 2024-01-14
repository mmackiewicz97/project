package com.example.project.repository;

import com.example.project.model.Kategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface KategorieRepository extends JpaRepository<Kategorie, Integer> {

}

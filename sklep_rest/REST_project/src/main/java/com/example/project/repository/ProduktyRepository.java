package com.example.project.repository;

import com.example.project.model.Produkty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface ProduktyRepository extends JpaRepository<Produkty, Integer> {
    
}

package com.example.project.repository;

import com.example.project.model.Adres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface AdresRepository extends JpaRepository<Adres, Integer> {

}
